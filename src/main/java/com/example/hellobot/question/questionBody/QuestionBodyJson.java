package com.example.hellobot.question.questionBody;

import com.example.hellobot.chatRoom.chatMessage.messageBody.ChatMessageBodyType;
import lombok.Data;
import lombok.ToString;

/**
 * @Author Ryu
 * @create 2020/10/19 9:52 오후
 */

@Data
public class QuestionBodyJson {
    ChatMessageBodyType type;
    String data;
}
