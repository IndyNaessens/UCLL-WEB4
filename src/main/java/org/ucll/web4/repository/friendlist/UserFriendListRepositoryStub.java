package org.ucll.web4.repository.friendlist;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserFriendListRepositoryStub implements UserFriendListRepository {

    private final HashMap<UUID, Set<UUID>> friendMap;

    public UserFriendListRepositoryStub() {
        friendMap = new HashMap<>();
    }

    @Override
    public void addFriend(UUID userId, UUID friendId) {
        if (!friendMap.containsKey(userId)) {
            friendMap.put(userId, new HashSet<>());
        }

        friendMap.get(userId).add(friendId);
    }

    @Override
    public List<UUID> getFriends(UUID userId) {
        if (!friendMap.containsKey(userId)) {
            friendMap.put(userId, new HashSet<>());
        }

        return new ArrayList<>(friendMap.get(userId));
    }
}
