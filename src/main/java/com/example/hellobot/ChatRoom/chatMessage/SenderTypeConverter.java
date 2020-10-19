package com.example.hellobot.chatRoom.chatMessage;

import com.example.hellobot.common.dataType.AbstractBaseEnumConverter;
import com.example.hellobot.contents.ContentsType;

/**
 * @Author Ryu
 * @create 2020/10/17 9:06 오후
 */
public class SenderTypeConverter extends AbstractBaseEnumConverter<ContentsType, String> {
    @Override
    protected ContentsType[] getValueList() {
        return ContentsType.values();
    }
}
