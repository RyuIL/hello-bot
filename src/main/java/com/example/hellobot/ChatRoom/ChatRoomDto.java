package com.example.hellobot.chatRoom;

import com.example.hellobot.chatRoom.chatMessage.*;
import com.example.hellobot.chatRoom.chatMessage.messageBody.ChatMessageBody;
import com.example.hellobot.question.QuestionStatusType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author Ryu
 * @create 2020/10/15 9:17 오후
 */
public class ChatRoomDto {

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CreationReq {
        private Long accountId;
        private Long botId;

        public ChatRoom toEntity(){
            return ChatRoom.builder()
                    .accountId(this.accountId)
                    .botId(this.botId)
                    .build();
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class MessageCreationReq {
        private Long chatRoomId;
        private Long senderId;
        private Long botId;
        private Long contentsId;
        private QuestionStatusType questionStatusType;
        private SenderType senderType;
        private ChatMessageBody chatMessageBody;
    }


    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class AccountIdAndBotId {
        private Long accountId;
        private Long botId;
    }

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Res {
        private Long id;
        private Long accountId;
        private Long botId;
        private List<ChatMessageDto.Res> chatMessages;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
}
