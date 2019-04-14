package org.ucll.web4.repository.chat;

import org.springframework.stereotype.Repository;
import org.ucll.web4.entity.ChatMessageEntity;
import org.ucll.web4.entity.ChatPairEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ChatMessageRepositoryStub implements ChatMessageRepository {

    private final Map<ChatPairEntity,List<ChatMessageEntity>> chatMessages;


    public ChatMessageRepositoryStub(){
        this.chatMessages = new HashMap<>();
    }

    @Override
    public void add(ChatMessageEntity message) {
        List<ChatMessageEntity> messages = chatMessages.get(new ChatPairEntity(message.getSender(),message.getReceiver()));

        messages.add(message);
    }

    @Override
    public List<ChatMessageEntity> getAll(ChatPairEntity chatPair) {
        return chatMessages.get(chatPair);
    }

    @Override
    public void createChatPair(ChatPairEntity pair) {
        chatMessages.put(pair,new ArrayList<>());
    }

    @Override
    public boolean exists(ChatPairEntity pair) {
        return chatMessages.containsKey(pair);
    }
}
