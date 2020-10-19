package com.example.hellobot.contents;

import com.example.hellobot.common.dataType.BaseEnumCode;
import lombok.Getter;

/**
 * @Author Ryu
 * @create 2020/10/15 11:37 오후
 */

@Getter
public enum ContentsType implements BaseEnumCode<String> {
    TAROT("TAROT"), FORTUNE("FORTUNE");

    private final String contentsType;

    ContentsType(String contentsType) { this.contentsType = contentsType; }

    @Override
    public String toString() {
        return this.contentsType;
    }

    @Override
    public String getValue() {
        return this.contentsType;
    }
}
