package com.example.hellobot.question;

import com.example.hellobot.contents.Contents;
import com.example.hellobot.question.questionBody.QuestionBodyDto;
import com.example.hellobot.question.userAnswer.UserAnswerBody;
import lombok.*;

import java.util.List;

/**
 * @Author Ryu
 * @create 2020/10/18 4:06 오후
 */
public class QuestionDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UserAnswerTextBodyCreationReq{
        Long id;
        Long contentsId;
        QuestionStatusType status;
        UserAnswerBody.Text userAnswerBody;

        protected Question toEntity(){
            return Question.builder()
                    .id(this.id)
                    .contents(Contents.builder().id(contentsId).build())
                    .status(this.status)
                    .userAnswerBody(this.userAnswerBody)
                    .build();

        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UserAnswerTarotBodyCreationReq{
        Long id;
        Long contentsId;
        QuestionStatusType status;
        UserAnswerBody.Tarot userAnswerBody;

        protected Question toEntity(){
            return Question.builder()
                    .id(this.id)
                    .contents(Contents.builder().id(contentsId).build())
                    .status(this.status)
                    .userAnswerBody(this.userAnswerBody)
                    .build();

        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UserAnswerEvaluateSkillBodyCreationReq{
        Long id;
        Long contentsId;
        QuestionStatusType status;
        UserAnswerBody.EvaluateSkill userAnswerBody;

        protected Question toEntity(){
            return Question.builder()
                    .id(this.id)
                    .contents(Contents.builder().id(contentsId).build())
                    .status(this.status)
                    .userAnswerBody(this.userAnswerBody)
                    .build();

        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UserAnswerSelectBodyCreationReq{
        Long id;
        Long contentsId;
        QuestionStatusType status;
        UserAnswerBody.Select userAnswerBody;

        protected Question toEntity(){
            return Question.builder()
                    .id(this.id)
                    .contents(Contents.builder().id(contentsId).build())
                    .status(this.status)
                    .userAnswerBody(this.userAnswerBody)
                    .build();

        }
    }

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Res{
        Long id;
        Long contentsId;
        QuestionStatusType status;
        List<QuestionBodyDto.Res> questionBodies;
        UserAnswerBody userAnswerBody;
    }
}
