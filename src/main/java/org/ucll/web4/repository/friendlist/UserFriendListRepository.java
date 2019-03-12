package org.ucll.web4.repository.friendlist;

import java.util.List;
import java.util.UUID;

public interface UserFriendListRepository {

    void addFriend(UUID userId, UUID friendId);

    List<UUID> getFriends(UUID userId);
}
