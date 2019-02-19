package org.ucll.web4.user.repository;

import org.ucll.web4.user.UserEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class UserRepositoryStub implements UserRepository {

    private final HashMap<UUID, UserEntity> users;

    public UserRepositoryStub(){
        users = new HashMap<>();

        UserEntity user1 = new UserEntity.Builder()
                .withId(UUID.fromString("14ed4726-243c-4dc6-a352-15d658f2ce31"))
                .withFirstName("Indy")
                .withLastName("Naessens")
                .withPassword("12345678")
                .withEmail("indy.naessens@pm.me")
                .withDefaultStatus()
                .build();

        UserEntity user2 = new UserEntity.Builder()
                .withId(UUID.fromString("408e3eab-dcfd-4620-8c6a-e252a19bbe1c"))
                .withFirstName("Wesly")
                .withLastName("Naessens")
                .withPassword("87654321")
                .withEmail("wesly@naessens.net")
                .withDefaultStatus()
                .build();

        create(user1,user1.getUserId());
        create(user2,user2.getUserId());
    }

    @Override
    public void create(UserEntity entity, UUID key) {
        users.put(key,entity);
    }

    @Override
    public UserEntity get(UUID key) {
        return users.get(key);
    }

    @Override
    public void update(UserEntity entity) {
        users.replace(entity.getUserId(),entity);
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
