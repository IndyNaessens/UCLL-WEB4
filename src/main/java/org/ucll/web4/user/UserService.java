package org.ucll.web4.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ucll.web4.exceptions.AlreadyBefriendedException;
import org.ucll.web4.exceptions.ArgumentNullException;
import org.ucll.web4.exceptions.UserDoesNotExistException;
import org.ucll.web4.user.repository.UserFriendListRepository;
import org.ucll.web4.user.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserFriendListRepository friendListRepository;

    public UserService(@Autowired UserRepository userRepository,@Autowired UserFriendListRepository friendListRepository){
        this.userRepository = userRepository;
        this.friendListRepository = friendListRepository;
    }

    //change the status of a user
    public void changeUserStatus(UUID userId, String newStatus){
        //checks
        if(userId == null || newStatus == null) throw new ArgumentNullException();
        if(!userRepository.exists(userId)) throw new UserDoesNotExistException();

        //get the user and change status
        UserEntity user = userRepository.get(userId);
        user.setStatus(newStatus);
    }

    //add a user to the friend list of a user
    public void addFriend(UUID userId, UUID newFriendId){
        //checks
        if(userId == null || newFriendId == null) throw new ArgumentNullException();
        if(!(userRepository.exists(userId) && userRepository.exists(newFriendId))) throw new UserDoesNotExistException();

        UserEntity owner = userRepository.get(userId);
        UserEntity newFriend = userRepository.get(newFriendId);

        List<UserEntity> ownerFriends = friendListRepository.getFriends(owner);
        if(ownerFriends.contains(newFriend)) throw new AlreadyBefriendedException();

        //add friend
        friendListRepository.addFriend(owner,newFriend);
    }

    //get an overview of the friends of a user
    public List<UserEntity> getFriendList(UUID userId){
        //checks
        if(userId == null) throw new ArgumentNullException();
        if(!userRepository.exists(userId)) throw new UserDoesNotExistException();

        //get friends
        UserEntity owner = userRepository.get(userId);
        return friendListRepository.getFriends(owner);
    }

    public List<UserEntity> getUserOverview(){
        return userRepository.getAll();
    }
}
