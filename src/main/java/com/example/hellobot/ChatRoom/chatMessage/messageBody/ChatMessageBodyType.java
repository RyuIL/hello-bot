package com.example.hellobot.chatRoom.chatMessage.messageBody;

import com.example.hellobot.common.dataType.BaseEnumCode;

/**
 * @Author Ryu
 * @create 2020/10/19 3:37 오후
 */
public enum  ChatMessageBodyType implements BaseEnumCode<String> {
    IMAGE("image"), TEXT("text"), LINK("link");

    private final String bodyType;

    ChatMessageBodyType(String bt) {this.bodyType = bt;}

    @Override
    public String getValue() {
        return this.bodyType;
    }

    @Override
    public String toString() {
        return this.bodyType;
    }
}
