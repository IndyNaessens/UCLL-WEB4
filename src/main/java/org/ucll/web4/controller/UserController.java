package org.ucll.web4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;
import org.ucll.web4.dto.AddFriendDto;
import org.ucll.web4.dto.ChangeStatusDto;
import org.ucll.web4.dto.FriendDto;
import org.ucll.web4.dto.UserRegistrationDto;
import org.ucll.web4.entity.UserEntity;
import org.ucll.web4.service.UserService;
import org.ucll.web4.spring.security.CustomUserDetails;

import javax.validation.Valid;
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

    //set status
    @PutMapping(value = "/status")
    @ResponseStatus(HttpStatus.OK)
    public HttpStatus changeStatus(@AuthenticationPrincipal CustomUserDetails userDetails,@Valid ChangeStatusDto changeStatusDto) {
        userService.changeUserStatus(userDetails.getUserId(), changeStatusDto.getStatus());

        return HttpStatus.OK; //avoid empty response because firefox gives error "XML Parsing Error: no root element found"
    }

    @GetMapping("/friend")
    @ResponseStatus(HttpStatus.OK)
    public List<FriendDto> getFriendList(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return userService.getFriendList(userDetails.getUserId());
    }

    @PostMapping("/friend")
    @ResponseStatus(HttpStatus.CREATED)
    public HttpStatus addFriend(@AuthenticationPrincipal CustomUserDetails userDetails, @Valid AddFriendDto addFriendDto) {
        UUID userIdFriend = userService.getUserIdFromEmail(addFriendDto.getEmail());
        UUID userId = userDetails.getUserId();

        userService.addFriend(userId, userIdFriend);
        userService.addFriend(userIdFriend, userId);

        return HttpStatus.CREATED; //avoid empty response because firefox gives error "XML Parsing Error: no root element found"
    }
}
