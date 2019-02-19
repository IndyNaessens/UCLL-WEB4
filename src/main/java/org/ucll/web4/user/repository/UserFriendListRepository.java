package org.ucll.web4.user.repository;


import org.springframework.stereotype.Repository;
import org.ucll.web4.user.UserEntity;

import java.util.List;

@Repository
public interface UserFriendListRepository {

    void addFriend(UserEntity owner,UserEntity newFriend);

    List<UserEntity> getFriends(UserEntity owner);
}
