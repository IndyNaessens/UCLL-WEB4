package org.ucll.web4.user.dto;

import java.util.UUID;

public class ChangeStatusDto {

    private UUID userId;
    private String newStatus;

    public ChangeStatusDto(){}

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }
}
