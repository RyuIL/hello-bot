package com.example.hellobot.chatRoom.chatMessage;

import com.example.hellobot.chatRoom.chatMessage.messageBody.ChatMessageBody;
import com.example.hellobot.chatRoom.chatMessage.messageBody.ChatMessageBodyConverter;
import com.example.hellobot.chatRoom.ChatRoom;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @Author Ryu
 * @create 2020/10/16 5:17 오후
 */

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessage {

    @Builder
    public ChatMessage(ChatRoom chatRoom, Long senderId, Long botId, Long contentsId, SenderType senderType, ChatMessageBody chatMessageBody, Long pickQuestionId) {
        this.chatRoom = chatRoom;
        this.senderId = senderId;
        this.botId = botId;
        this.contentsId = contentsId;
        this.senderType = senderType;
        this.chatMessageBody = chatMessageBody;
        this.pickQuestionId = pickQuestionId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chatRoomId", updatable = false)
    private ChatRoom chatRoom;

    private Long senderId;

    private Long botId;

    private Long contentsId;

    private Long pickQuestionId;

    @Convert(converter = SenderTypeConverter.class)
    private SenderType senderType;

    @Column(columnDefinition = "JSON")
    @Convert(converter = ChatMessageBodyConverter.class)
    private ChatMessageBody chatMessageBody;

    @CreationTimestamp
    private LocalDateTime createdAt;


    public ChatMessageDto.Res toDto(){
        return ChatMessageDto.Res.builder()
                .id(this.id)
                .chatRoomId(this.chatRoom.getId())
                .botId(this.botId)
                .contentsId(this.contentsId)
                .senderType(this.senderType)
                .chatMessageBody(this.chatMessageBody)
                .createdAt(this.createdAt)
                .build();
    }
}
