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
    public void create(UserEntity entity, UUID key) {
        users.put(key, entity);
    }

    @Override
    public UserEntity get(UUID key) {
        return users.get(key);
    }

    @Override
    public void update(UserEntity entity) {
        users.replace(entity.getUserId(), entity);
    }

    @Override
    public List<UserEntity> getAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public boolean exists(UUID key) {
        return users.containsKey(key);
    }
}
