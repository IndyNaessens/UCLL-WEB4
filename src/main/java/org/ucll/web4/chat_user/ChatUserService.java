package org.ucll.web4.chat_user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ucll.web4.exceptions.ChatUserAlreadyExistsException;

@Service
public class ChatUserService {

    private final ChatUserRepository repository;

    public ChatUserService(@Autowired ChatUserRepository repository){
        this.repository = repository;
    }

    public void registerChatUser(ChatUser chatUser){
        if(chatUser == null) throw new IllegalArgumentException();

        //check if user already exists
        if(repository.existsById(chatUser.getId())) throw new ChatUserAlreadyExistsException();

        //persist chat user
        repository.saveAndFlush(chatUser);
    }
}
