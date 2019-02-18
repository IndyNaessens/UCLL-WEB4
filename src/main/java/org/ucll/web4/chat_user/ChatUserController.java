package org.ucll.web4.chat_user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatUserController {

    private final ChatUserService userService;

    public ChatUserController(@Autowired ChatUserService userService){
        this.userService = userService;
    }


}
