package com.example.hellobot.question.questionBody;

import com.example.hellobot.question.Question;
import lombok.*;

/**
 * @Author Ryu
 * @create 2020/10/18 4:54 오후
 */
public class QuestionBodyDto {
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CreationRequest{
        Long id;
        Long questionId;
        QuestionBodyJson chatMessageBody;
        Double typingSpeed;

        protected QuestionBody toEntity(){
            return QuestionBody.builder()
                    .id(this.id)
                    .question(Question.builder().id(questionId).build())
                    .typingSpeed(this.typingSpeed)
                    .chatMessageBody(this.chatMessageBody)
                    .build();

        }
    }

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Res{
        Long id;
        Long questionId;
        QuestionBodyJson chatMessageBody;
        Double typingSpeed;
    }
}
