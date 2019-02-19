package org.ucll.web4.user.repository;

import org.ucll.web4.user.UserEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserFriendListRepositoryStub implements UserFriendListRepository {

    private final HashMap<UserEntity,List<UserEntity>> friendMap;

    public UserFriendListRepositoryStub(){
        friendMap = new HashMap<>();
    }

    @Override
    public void addFriend(UserEntity owner,UserEntity newFriend) {
        if(!friendMap.containsKey(owner)){
            friendMap.put(owner,new ArrayList<>());
        }

        System.out.println("added friend " + newFriend.getEmail());
        friendMap.get(owner).add(newFriend);
    }

    @Override
    public List<UserEntity> getFriends(UserEntity owner) {
        List<UserEntity> friends = friendMap.get(owner);

        if(friends == null) return new ArrayList<>(); //there is no entry for owner, he has not added any friends yet
        return friends;
    }
}
