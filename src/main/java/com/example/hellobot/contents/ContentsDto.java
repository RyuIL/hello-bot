package com.example.hellobot.contents;

import com.example.hellobot.question.QuestionDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

/**
 * @Author Ryu
 * @create 2020/10/18 12:01 오전
 */
public class ContentsDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CreationReq {
        private Long id;
        private Long botId;
        @ApiModelProperty(value = "타입", required = true)
        private ContentsType type = ContentsType.TAROT;
        @ApiModelProperty(value = "이름", required = true)
        private String name;
        @ApiModelProperty(value = "보여질 이름", required = true)
        private String displayName;
        @ApiModelProperty(value = "설명")
        private String description;

        public Contents toEntity(){
            return Contents.builder()
                    .id(this.id)
                    .botId(this.botId)
                    .name(this.name)
                    .description(this.description)
                    .displayName(this.displayName)
                    .type(this.type)
                    .build();
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Res {
        private Long id;
        private Long botId;
        @ApiModelProperty(value = "타입")
        private ContentsType type;
        @ApiModelProperty(value = "이름")
        private String name;
        @ApiModelProperty(value = "보여질 이름")
        private String displayName;
        @ApiModelProperty(value = "질문들")
        private List<QuestionDto.Res> questions;
        @ApiModelProperty(value = "설명")
        private String description;
    }
}
