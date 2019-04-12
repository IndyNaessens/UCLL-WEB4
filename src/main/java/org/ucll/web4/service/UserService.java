package org.ucll.web4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ucll.web4.dto.FriendDto;
import org.ucll.web4.entity.BlogEntity;
import org.ucll.web4.entity.UserEntity;
import org.ucll.web4.exception.ArgumentEmptyException;
import org.ucll.web4.exception.ArgumentNullException;
import org.ucll.web4.exception.UserDoesNotExistException;
import org.ucll.web4.repository.blog.BlogRepository;
import org.ucll.web4.repository.friendlist.UserFriendListRepository;
import org.ucll.web4.repository.user.UserRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserFriendListRepository friendListRepository;

    public UserService(@Autowired UserRepository userRepository, @Autowired UserFriendListRepository friendListRepository) {
        this.userRepository = userRepository;
        this.friendListRepository = friendListRepository;
    }

    //change the status of a user
    public void changeUserStatus(UUID userId, String newStatus) {
        //checks
        if (userId == null || newStatus == null) throw new ArgumentNullException();
        if (newStatus.trim().isEmpty()) throw new ArgumentEmptyException();
        if (!userRepository.exists(userId)) throw new UserDoesNotExistException();

        //get the user and change status
        UserEntity user = userRepository.get(userId);
        user.setStatus(newStatus);
    }

    //add a user to the friend list of a user
    public void addFriend(UUID userId, UUID userIdFriend) {
        //checks
        if (userId == null || userIdFriend == null) throw new ArgumentNullException();
        if (!(userRepository.exists(userId) && userRepository.exists(userIdFriend))) throw new UserDoesNotExistException();

        //add friend
        friendListRepository.addFriend(userId, userIdFriend);
    }

    //get an overview of the friends of a user
    public List<FriendDto> getFriendList(UUID userId) {
        //checks
        if (userId == null) throw new ArgumentNullException();
        if (!userRepository.exists(userId)) throw new UserDoesNotExistException();

        //get friends
        return friendListRepository.getFriends(userId)
                .stream()
                .map(userRepository::get)   //costly
                .map(FriendDto::new)
                .collect(Collectors.toList());
    }

    public UUID getUserIdFromEmail(String email) {
        //checks
        if (email == null) throw new ArgumentNullException();
        if (email.trim().isEmpty()) throw new ArgumentEmptyException();

        return userRepository.getAll()
                .stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElseThrow(UserDoesNotExistException::new)
                .getUserId();
    }

    public List<UserEntity> getUserOverview() {
        return userRepository.getAll();
    }
}
