package com.example.hellobot.question.userAnswer;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Convert;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Ryu
 * @create 2020/10/18 2:51 오후
 */

@Data
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", include = JsonTypeInfo.As.EXISTING_PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = UserAnswerBody.EvaluateSkill.class, name = "evaluateSkill"),
        @JsonSubTypes.Type(value = UserAnswerBody.Tarot.class, name= "tarot"),
        @JsonSubTypes.Type(value = UserAnswerBody.Select.class, name= "select"),
        @JsonSubTypes.Type(value = UserAnswerBody.Text.class, name= "text"),
        @JsonSubTypes.Type(value = Emoji.class, name= "emoji"),
})
public abstract class UserAnswerBody {
    @Data
//    @EqualsAndHashCode(callSuper = false)
    public static class EvaluateSkill extends UserAnswerBody {
        private int pickCount;
        private List<Emoji> emojis = new ArrayList<>();
        @Convert(converter = UserAnswerTypeConverter.class)
        private UserAnswerType type = UserAnswerType.EVAL_SKILL;
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class Tarot extends UserAnswerBody {
        @ApiModelProperty(value = "뽑아야하는 개수", required = true)
        private int pickCount;

        @ApiModelProperty(value = "총 개수", required = true)
        private int totalCount;

        @ApiModelProperty(value = "타로 앞면 image")
        private String tarotFrontImage;

        @ApiModelProperty(value = "타로 뒷면 image")
        private String tarotBackImage;

        @ApiModelProperty(value = "답변 타입")
        @Convert(converter = UserAnswerTypeConverter.class)
        private UserAnswerType type = UserAnswerType.TAROT;

        @ApiModelProperty(value = "다음 질문 아이디", required = true)
        private Long nextQuestionId;
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class Text extends UserAnswerBody {
        @ApiModelProperty(value = "답변 타입")
        @Convert(converter = UserAnswerTypeConverter.class)
        private UserAnswerType type = UserAnswerType.TEXT;
        @ApiModelProperty(value = "다음 질문 아이디", required = true)
        private Long nextQuestionId;
    }


    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class Select extends UserAnswerBody {

        @Data
        public static class SelectBody{
            @ApiModelProperty(value = "text")
            private String text;
            @ApiModelProperty(value = "다음 질문 아이디", required = true)
            private Long nextQuestionId;
        }

        @ApiModelProperty(value = "선택 개수", required = true)
        private int pickCount;

        @ApiModelProperty(value = "총 개수", required = true)
        private int totalCount;

        @ApiModelProperty(value = "선택지", required = true)
        private List<SelectBody> choiceList;

        @ApiModelProperty(value = "답변 타입")
        @Convert(converter = UserAnswerTypeConverter.class)
        private UserAnswerType type = UserAnswerType.SELECT;
    }
}
