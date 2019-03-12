package org.ucll.web4.repository.friendlist;

import org.ucll.web4.entity.UserEntity;

import java.util.List;

public interface UserFriendListRepository {

    void addFriend(UserEntity owner, UserEntity newFriend);

    List<UserEntity> getFriends(UserEntity owner);
}
