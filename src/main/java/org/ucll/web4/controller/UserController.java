package org.ucll.web4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.ucll.web4.dto.AddFriendDto;
import org.ucll.web4.dto.ChangeStatusDto;
import org.ucll.web4.entity.UserEntity;
import org.ucll.web4.service.UserService;
import org.ucll.web4.spring.security.CustomUserDetails;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserEntity> getUserOverview() {
        return userService.getUserOverview();
    }

    @PutMapping("/status")
    @ResponseStatus(HttpStatus.OK)
    public void changeStatus(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody ChangeStatusDto changeStatusDto) {
        userService.changeUserStatus(userDetails.getUserId(), changeStatusDto.getNewStatus());
    }

    @GetMapping("/{userId}/friends")
    @ResponseStatus(HttpStatus.OK)
    public List<UserEntity> getFriendList(@PathVariable UUID userId) {
        return userService.getFriendList(userId);
    }

    @PostMapping("/friend")
    @ResponseStatus(HttpStatus.CREATED)
    public void addFriend(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody AddFriendDto addFriendDto) {
        UUID newFriendId = userService.getUserIdFromEmail(addFriendDto.getEmail());
        UUID ownerId = userDetails.getUserId();

        userService.addFriend(ownerId, newFriendId);
        userService.addFriend(newFriendId, ownerId);
    }
}
