package com.example.hellobot.chatRoom.chatMessage;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Ryu
 * @create 2020/10/17 10:15 오후
 */
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}
