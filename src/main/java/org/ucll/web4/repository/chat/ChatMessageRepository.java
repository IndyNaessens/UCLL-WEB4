package org.ucll.web4.repository.chat;

import org.ucll.web4.entity.ChatMessageEntity;
import org.ucll.web4.entity.ChatPairEntity;

import java.util.List;

public interface ChatMessageRepository {

    void add(ChatMessageEntity chatMessageEntity);

    List<ChatMessageEntity> getAll(ChatPairEntity chatPair);

    void createChatPair(ChatPairEntity pair);

    boolean exists(ChatPairEntity pair);

}
