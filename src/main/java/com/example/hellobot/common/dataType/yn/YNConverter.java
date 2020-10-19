package com.example.hellobot.common.dataType.yn;

import com.example.hellobot.common.dataType.AbstractBaseEnumConverter;

import javax.persistence.Converter;

@Converter(autoApply = false)
public class YNConverter extends AbstractBaseEnumConverter<YN, String> {
    @Override
    protected YN[] getValueList() {
        return YN.values();
    }
}