package com.example.hellobot.Account;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * @Author Ryu
 * @create 2020/10/15 9:17 오후
 */

public class AccountDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CreationReq {
        private String ipAddress;
        private String macAddress;

        @Builder
        public CreationReq(String ipAddress, String macAddress){
            this.ipAddress = ipAddress;
            this.macAddress = macAddress;
        }

        public Account toEntity(){
            return Account.builder()
                    .ipAddress(this.ipAddress)
                    .macAddress(this.macAddress)
                    .build();
        }
    }


    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Res {
        private String ipAddress;
        private String macAddress;

        public Res(final Account account){
            this.ipAddress = account.getIpAddress();
            this.macAddress = account.getMacAddress();
        }

    }
}
