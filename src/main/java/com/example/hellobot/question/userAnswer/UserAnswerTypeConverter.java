package com.example.hellobot.question.userAnswer;

import com.example.hellobot.common.dataType.AbstractBaseEnumConverter;

/**
 * @Author Ryu
 * @create 2020/10/18 3:00 오후
 */
public class UserAnswerTypeConverter extends AbstractBaseEnumConverter<UserAnswerType, String> {
    @Override
    protected UserAnswerType[] getValueList() {
        return UserAnswerType.values();
    }
}
