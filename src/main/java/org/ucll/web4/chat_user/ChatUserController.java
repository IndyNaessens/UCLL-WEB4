package org.ucll.web4.chat_user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.ucll.web4.chat_user.dto.ChangeStatusDto;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class ChatUserController {

    private final ChatUserService userService;

    public ChatUserController(@Autowired ChatUserService userService){
        this.userService = userService;
    }

    @PutMapping("/status")
    @ResponseStatus(HttpStatus.OK)
    public void updateChatUser(@RequestBody @Valid  ChangeStatusDto changeStatusDto){
        userService.updateStatus(changeStatusDto.getId(),changeStatusDto.getNewStatus());
    }

    //postman test
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ChatUser> getAll(){
        return userService.getAll();
    }
}
