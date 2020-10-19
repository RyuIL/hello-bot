package com.example.hellobot.chatRoom.chatMessage;

import com.example.hellobot.common.dataType.BaseEnumCode;

/**
 * @Author Ryu
 * @create 2020/10/17 6:57 오후
 */
public enum SenderType implements BaseEnumCode<String> {
    USER("user"), ADMIN("admin"), BOT("bot");

    private final String sender;

    SenderType(String sd) {this.sender = sd;}


    @Override
    public String getValue() {
        return this.sender;
    }

    @Override
    public String toString() {
        return this.sender;
    }
}
