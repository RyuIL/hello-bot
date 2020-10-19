package com.example.hellobot.contents;

import com.example.hellobot.common.dataType.AbstractBaseEnumConverter;

import javax.persistence.Converter;

/**
 * @Author Ryu
 * @create 2020/10/15 11:49 오후
 */

@Converter(autoApply = false)
public class ContentsTypeConverter extends AbstractBaseEnumConverter<ContentsType, String> {
    @Override
    protected ContentsType[] getValueList() {
        return ContentsType.values();
    }
}
