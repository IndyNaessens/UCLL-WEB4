package org.ucll.web4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ucll.web4.entity.ChatMessageEntity;
import org.ucll.web4.entity.ChatPairEntity;
import org.ucll.web4.exception.ArgumentNullException;
import org.ucll.web4.repository.chat.ChatMessageRepository;

import java.util.List;

@Service
public class ChatService {

    private final ChatMessageRepository chatMessageRepository;

    public ChatService(@Autowired ChatMessageRepository chatMessageRepository){
        this.chatMessageRepository = chatMessageRepository;
    }

    public void sendChatMessage(ChatMessageEntity chatMessage){
        if(chatMessage == null) throw new ArgumentNullException();
        if(!(chatMessageRepository.exists(new ChatPairEntity(chatMessage.getSender(),chatMessage.getReceiver()))))
            chatMessageRepository.createChatPair(new ChatPairEntity(chatMessage.getSender(),chatMessage.getReceiver()));

        chatMessageRepository.add(chatMessage);
    }

    public List<ChatMessageEntity> getAllChatMessages(ChatPairEntity chatPair){
        if(chatPair == null) throw new ArgumentNullException();
        if(!(chatMessageRepository.exists(chatPair))) chatMessageRepository.createChatPair(chatPair);

        return chatMessageRepository.getAll(chatPair);
    }
}
