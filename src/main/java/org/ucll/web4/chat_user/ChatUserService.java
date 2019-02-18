package org.ucll.web4.chat_user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ucll.web4.exceptions.ArgumentEmptyException;
import org.ucll.web4.exceptions.ArgumentNullException;
import org.ucll.web4.exceptions.ChatUserAlreadyExistsException;

import java.util.List;
import java.util.UUID;

@Service
public class ChatUserService {

    private final ChatUserRepository repository;

    public ChatUserService(@Autowired ChatUserRepository repository){
        this.repository = repository;
    }

    //registers a new chat user
    public void register(ChatUser chatUser){
        if(chatUser == null) throw new ArgumentNullException();

        if(repository.existsById(chatUser.getUserId())) throw new ChatUserAlreadyExistsException();

        repository.saveAndFlush(chatUser);
    }

    //update the status of the chat user with the provided id
    public void updateStatus(UUID chatUserId, String newStatus){
        if(chatUserId == null || newStatus == null) throw new ArgumentNullException();

        if(newStatus.isEmpty()) throw new ArgumentEmptyException();

        repository.updateStatus(chatUserId,newStatus);
    }

    //postman test
    public List<ChatUser> getAll(){
        return repository.findAll();
    }
}
