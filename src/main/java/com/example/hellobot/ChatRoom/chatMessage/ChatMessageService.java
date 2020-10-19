package com.example.hellobot.chatRoom.chatMessage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Author Ryu
 * @create 2020/10/17 10:15 오후
 */


@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
}
