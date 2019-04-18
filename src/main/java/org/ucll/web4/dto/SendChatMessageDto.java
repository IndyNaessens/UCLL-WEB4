package org.ucll.web4.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

public class SendChatMessageDto {

    @NotNull
    private UUID userIdReceiver;

    @NotBlank
    private String message;

    private LocalDateTime sendDate = LocalDateTime.now();

    public SendChatMessageDto(UUID userIdReceiver, String message) {
        this.userIdReceiver = userIdReceiver;
        this.message = message;
    }

    public UUID getUserIdReceiver() {
        return userIdReceiver;
    }

    public void setUserIdReceiver(UUID userIdReceiver) {
        this.userIdReceiver = userIdReceiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getSendDate() {
        return sendDate;
    }
}
