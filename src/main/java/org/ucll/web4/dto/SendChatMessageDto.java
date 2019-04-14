package org.ucll.web4.dto;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class SendChatMessageDto {

    @NotBlank
    private String emailReceiver;

    @NotBlank
    private String message;

    private LocalDateTime sendDate = LocalDateTime.now();

    public SendChatMessageDto(String emailReceiver, String message) {
        this.emailReceiver = emailReceiver;
        this.message = message;
    }

    public String getEmailReceiver() {
        return emailReceiver;
    }

    public void setEmailReceiver(String emailReceiver) {
        this.emailReceiver = emailReceiver;
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
