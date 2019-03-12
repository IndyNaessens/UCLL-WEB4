package org.ucll.web4.repository.user;

import org.springframework.stereotype.Repository;
import org.ucll.web4.entity.UserEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Repository
public class UserRepositoryStub implements UserRepository {

    private final HashMap<UUID, UserEntity> users;

    public UserRepositoryStub() {
        users = new HashMap<>();
    }

    @Override
    public void create(UserEntity user, UUID userId) {
        users.put(userId, user);
    }

    @Override
    public UserEntity get(UUID userId) {
        return users.get(userId);
    }

    @Override
    public void update(UserEntity user) {
        users.replace(user.getUserId(), user);
    }

    @Override
    public List<UserEntity> getAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public boolean exists(UUID userId) {
        return users.containsKey(userId);
    }
}
