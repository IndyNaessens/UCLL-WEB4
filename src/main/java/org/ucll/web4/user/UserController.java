package org.ucll.web4.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.ucll.web4.user.dto.AddFriendDto;
import org.ucll.web4.user.dto.ChangeStatusDto;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(@Autowired UserService userService){
        this.userService = userService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserEntity> getUserOverview(){
        return userService.getUserOverview();
    }

    @PutMapping("/status")
    @ResponseStatus(HttpStatus.OK)
    public void changeStatus(@RequestBody ChangeStatusDto dto){
        userService.changeUserStatus(dto.getUserId(),dto.getNewStatus());
    }

    @GetMapping("/{userId}/friends")
    @ResponseStatus(HttpStatus.OK)
    public List<UserEntity> getFriendList(@PathVariable UUID userId){
        return userService.getFriendList(userId);
    }

    @PostMapping("/friend")
    @ResponseStatus(HttpStatus.CREATED)
    public void addFriend(@RequestBody AddFriendDto dto){
        //friends are both ways
        userService.addFriend(dto.getOwner(),dto.getNewFriend());
        userService.addFriend(dto.getNewFriend(),dto.getOwner());
    }
}
