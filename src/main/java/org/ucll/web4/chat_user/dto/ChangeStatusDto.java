package org.ucll.web4.chat_user.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class ChangeStatusDto {

    @NotNull
    private UUID id;

    @NotBlank
    private String newStatus;

    public ChangeStatusDto(){

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }
}
