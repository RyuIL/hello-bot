package com.example.hellobot.chatRoom;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.hellobot.chatRoom.chatMessage.ChatMessage;

/**
 * @Author Ryu
 * @create 2020/10/15 9:17 오후
 * */

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom {

    @Builder
    public ChatRoom(Long id, Long accountId, Long botId) {
        this.id = id;
        this.accountId = accountId;
        this.botId = botId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long accountId;
    private Long botId;

    @OneToMany(mappedBy = "chatRoom", fetch = FetchType.LAZY)
    private Set<ChatMessage> chatMessages = new HashSet<>();

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;


    public ChatRoomDto.Res toDto(){
        return ChatRoomDto.Res.builder()
                .id(this.id)
                .accountId(this.accountId)
                .botId(this.botId)
                .chatMessages(this.chatMessages
                        .stream()
                        .sorted(Comparator.comparing(ChatMessage::getCreatedAt))
                        .map(ChatMessage::toDto)
                        .collect(Collectors.toList())
                )
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }
}
