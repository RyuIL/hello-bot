package com.example.hellobot.common.dataType.yn;

import com.example.hellobot.common.dataType.BaseEnumCode;
import lombok.Getter;

@Getter
public enum YN implements BaseEnumCode<String> {
    Y("Y"), N("N");

    private final String yn;

    YN(String yn) {
        this.yn = yn;
    }

    public String get() {
        return yn;
    }

    @Override
    public String toString() {
        return this.yn;
    }

    @Override
    public String getValue() {
        return this.yn;
    }
}
