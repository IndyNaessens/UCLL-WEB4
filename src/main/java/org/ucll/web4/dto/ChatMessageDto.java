package org.ucll.web4.dto;

import org.ucll.web4.entity.ChatMessageEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public class ChatMessageDto {

    private final String message;
    private final LocalDateTime sendDate;
    private final boolean sendByYou;

    private ChatMessageDto(String message, LocalDateTime sendDate,boolean sendByYou){
        this.message = message;
        this.sendDate = sendDate;
        this.sendByYou = sendByYou;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getSendDate() {
        return sendDate;
    }

    public boolean isSendByYou() {
        return sendByYou;
    }

    public static ChatMessageDto convertFromEntity(ChatMessageEntity chatMessage, UUID currentUser){
        if(chatMessage.getSender().equals(currentUser)){
            return new ChatMessageDto(chatMessage.getMessage(),chatMessage.getSendDate(),true);
        }else{
            return new ChatMessageDto(chatMessage.getMessage(),chatMessage.getSendDate(),false);
        }
    }
}
