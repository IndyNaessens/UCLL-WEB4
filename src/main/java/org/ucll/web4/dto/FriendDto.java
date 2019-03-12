package org.ucll.web4.dto;

import org.ucll.web4.entity.UserEntity;

import java.util.UUID;

public class FriendDto {

    private final UUID userId;
    private final String firstName;
    private final String lastName;
    private final String status;

    public FriendDto(UserEntity user){
        userId = user.getUserId();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        status = user.getStatus();
    }

    public UUID getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStatus() {
        return status;
    }
}
