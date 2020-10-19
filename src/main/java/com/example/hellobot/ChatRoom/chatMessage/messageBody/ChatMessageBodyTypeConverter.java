package com.example.hellobot.chatRoom.chatMessage.messageBody;

import com.example.hellobot.common.dataType.AbstractBaseEnumConverter;

/**
 * @Author Ryu
 * @create 2020/10/19 3:41 오후
 */


public class ChatMessageBodyTypeConverter extends AbstractBaseEnumConverter<ChatMessageBodyType, String> {
    @Override
    protected ChatMessageBodyType[] getValueList() {
        return ChatMessageBodyType.values();
    }
}
