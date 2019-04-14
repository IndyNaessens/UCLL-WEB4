package org.ucll.web4.entity;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class ChatMessageEntity {

    private final UUID sender;
    private final UUID receiver;
    private final String message;
    private final LocalDateTime sendDate;

    private ChatMessageEntity(UUID sender, UUID receiver, String message, LocalDateTime sendDate){
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.sendDate = sendDate;
    }

    public UUID getSender() {
        return sender;
    }

    public UUID getReceiver() {
        return receiver;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getSendDate() {
        return sendDate;
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof ChatMessageEntity)) return false;

        ChatMessageEntity temp = (ChatMessageEntity)o;
        return temp.getSender().equals(getSender()) &&
                temp.getReceiver().equals(getReceiver()) &&
                temp.getMessage().equals(getMessage()) &&
                temp.getSendDate().equals(getSendDate());
    }

    @Override
    public int hashCode(){
        return Objects.hash(sender, receiver,message,sendDate);
    }

    public static class Builder{
        private UUID sender;
        private UUID receiver;
        private String message;
        private LocalDateTime senDate;

        public Builder withMessage(String message){
            this.message = message;
            return this;
        }

        public Builder sendAt(LocalDateTime senDate){
            this.senDate = senDate;
            return this;
        }

        public Builder from(UUID sender){
            this.sender = sender;
            return this;
        }

        public Builder to(UUID receiver){
            this.receiver = receiver;
            return this;
        }

        public ChatMessageEntity build(){
            return new ChatMessageEntity(sender, receiver,message,senDate);
        }
    }
}
