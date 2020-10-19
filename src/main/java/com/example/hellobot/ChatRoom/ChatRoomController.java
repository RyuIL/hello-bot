package com.example.hellobot.chatRoom;

import com.example.hellobot.account.AccountService;
import com.example.hellobot.chatRoom.chatMessage.ChatMessageDto;
import com.example.hellobot.common.exception.DataNotFoundException;
import com.example.hellobot.question.QuestionDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

/**
 * @Author Ryu
 * @create 2020/10/19 5:46 오후
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/chat-room")
public class ChatRoomController {
    private final ChatRoomService chatRoomService;
    private AccountService accountService;

    @PostMapping("")
    @ApiOperation(value = "채팅방 생성")
    ChatRoomDto.Res insert(
            @RequestBody @ApiParam("채팅방 생성 request") ChatRoomDto.CreationReq req
    ) {
        return this.chatRoomService.insertChatRoom(req);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "채팅방 조회")
    ChatRoomDto.Res getById(
            @PathVariable("id") @ApiParam("id") Long id
    ) {
        return this.chatRoomService.getById(id);
    }

    @GetMapping
    @ApiOperation(value = "채팅방 조회 / account id, bot id")
    ChatRoomDto.Res getByAccountIdAndBotID(
            @RequestBody @ApiParam("account id, bot id DTO") ChatRoomDto.AccountIdAndBotId req
    ) {
        return this.chatRoomService.getByAccountIdAndBotId(req.getAccountId(), req.getBotId()).orElseThrow(DataNotFoundException::new);
    }

    @PostMapping("/message")
    @ApiOperation(value = "메세지 전송", notes = "senderType : USER, ADMIN, BOT")
    QuestionDto.Res sendMessage(
            @RequestBody @ApiParam("message dto") ChatMessageDto.CreationReq req
    ){
        return this.chatRoomService.sendMessage(req);
    }
}
