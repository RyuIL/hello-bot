package com.example.hellobot.account;

import com.example.hellobot.common.dataType.yn.YN;
import com.example.hellobot.common.dataType.yn.YNConverter;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Convert;


/**
 * @Author Ryu
 * @create 2020/10/15 9:17 오후
 */

public class AccountDto {
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CreationReq {
        @ApiModelProperty(value = "user ip address", required = true)
        private String ipAddress;

        @Builder
        public CreationReq(String ipAddress){
            this.ipAddress = ipAddress;
        }

        public Account toEntity(){
            return Account.builder()
                    .ipAddress(this.ipAddress)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public class UpdateName {
        @ApiModelProperty(value = "이름", required = true)
        private String name;
    }

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Res {
        private Long id;
        @ApiModelProperty(value = "ip address")
        private String ipAddress;
        @ApiModelProperty(value = "이름")
        private String name;
        @ApiModelProperty(value = "닉네임")
        private String nickName;
        @ApiModelProperty(value = "프로필이미지 url")
        private String profileImage;
        @ApiModelProperty(value = "활성여부")
        private YN active;
    }
}
