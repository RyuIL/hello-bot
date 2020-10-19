package com.example.hellobot.chatRoom.chatMessage;

import com.example.hellobot.chatRoom.ChatRoom;
import com.example.hellobot.chatRoom.chatMessage.messageBody.ChatMessageBody;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @Author Ryu
 * @create 2020/10/19 6:12 오후
 */
public class ChatMessageDto {
    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CreationReq {
        private Long chatRoomId;
        private Long senderId;
        private Long botId;
        private Long contentsId;
        private Long pickQuestionId;
        private SenderType senderType;
        private ChatMessageBody chatMessageBody;

        public ChatMessage toEntity(){
            return ChatMessage.builder()
                    .chatRoom(ChatRoom.builder().id(chatRoomId).build())
                    .botId(this.botId)
                    .senderId(this.senderId)
                    .contentsId(this.contentsId)
                    .pickQuestionId(this.pickQuestionId)
                    .senderType(this.senderType)
                    .chatMessageBody(this.chatMessageBody)
                    .build();
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Res {
        private Long id;
        private Long chatRoomId;
        private Long senderId;
        private Long botId;
        private Long contentsId;
        private Long pickQuestionId;
        private SenderType senderType;
        private ChatMessageBody chatMessageBody;
        private LocalDateTime createdAt;
    }


}
