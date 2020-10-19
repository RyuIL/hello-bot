package com.example.hellobot.bot;

import com.example.hellobot.common.dataType.yn.YN;
import com.example.hellobot.contents.Contents;
import com.example.hellobot.contents.ContentsDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Ryu
 * @create 2020/10/15 11:23 오후
 */
public class BotDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CreationRequest {
        private Long id;
        @ApiModelProperty(value = "이름", required = true)
        private String name;
        @ApiModelProperty(value = "보여질 이름", required = true)
        private String displayName;
        @ApiModelProperty(value = "설명")
        private String description;
        @ApiModelProperty(value = "이미지")
        private String profileImage;

        public Bot toEntity(){
            return Bot.builder()
                    .name(this.name)
                    .displayName(this.displayName)
                    .description(this.description)
                    .profileImage(this.profileImage)
                    .active(YN.Y)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Res {
        private Long id;
        @ApiModelProperty(value = "이름")
        private String name;
        @ApiModelProperty(value = "보여질 이름")
        private String displayName;
        @ApiModelProperty(value = "설명")
        private String description;
        @ApiModelProperty(value = "이미지")
        private String profileImage;
        @ApiModelProperty(value = "컨텐츠 리스트")
        private List<ContentsDto.Res> contents;
        @ApiModelProperty(value = "user ip address")
        private YN active;

        public Res(Bot bot){
            this.id = bot.getId();
            this.name = bot.getName();
            this.displayName = bot.getDisplayName();
            this.description = bot.getDescription();
            this.profileImage = bot.getProfileImage();
            this.active = bot.getActive();
            this.contents = bot.getContents()
                    .stream()
                    .sorted(Comparator.comparing(Contents::getId))
                    .map(Contents::toDto)
                    .collect(Collectors.toList());
        }
    }
}
