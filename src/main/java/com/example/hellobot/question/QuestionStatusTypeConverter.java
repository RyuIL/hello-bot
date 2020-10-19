package com.example.hellobot.question;

import com.example.hellobot.common.dataType.AbstractBaseEnumConverter;

import javax.persistence.Converter;

/**
 * @Author Ryu
 * @create 2020/10/19 5:35 오후
 */

@Converter(autoApply = true)
public class QuestionStatusTypeConverter extends AbstractBaseEnumConverter<QuestionStatusType, String> {
    @Override
    protected QuestionStatusType[] getValueList() {
        return QuestionStatusType.values();
    }
}
