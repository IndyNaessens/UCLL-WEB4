package org.ucll.web4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.ucll.web4.dto.ChatMessageDto;
import org.ucll.web4.dto.SendChatMessageDto;
import org.ucll.web4.entity.ChatMessageEntity;
import org.ucll.web4.entity.ChatPairEntity;
import org.ucll.web4.service.ChatService;
import org.ucll.web4.service.UserService;
import org.ucll.web4.spring.security.CustomUserDetails;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;
    private final UserService userService;

    public ChatController(@Autowired ChatService chatService, @Autowired UserService userService){
        this.chatService = chatService;
        this.userService = userService;
    }

    @GetMapping("/{email}")
    @ResponseStatus(HttpStatus.OK)
    public List<ChatMessageDto> getAllMessages(@PathVariable String email, @AuthenticationPrincipal CustomUserDetails userDetails){
        return chatService.getAllChatMessages(new ChatPairEntity(userService.getUserIdFromEmail(email), userDetails.getUserId()))
                .stream()
                .map(chatMessageEntity -> ChatMessageDto.convertFromEntity(chatMessageEntity,userDetails.getUserId()))
                .collect(Collectors.toList());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public HttpStatus sendChatMessage(@Valid @RequestBody SendChatMessageDto sendChatMessageDto, @AuthenticationPrincipal CustomUserDetails userDetails){
        UUID sender = userDetails.getUserId();
        UUID receiver = userService.getUserIdFromEmail(sendChatMessageDto.getEmailReceiver());

        ChatMessageEntity chatMessageEntity = new ChatMessageEntity.Builder()
                .from(sender)
                .to(receiver)
                .withMessage(sendChatMessageDto.getMessage())
                .sendAt(sendChatMessageDto.getSendDate())
                .build();

        chatService.sendChatMessage(chatMessageEntity);

        return HttpStatus.OK;
    }
}
