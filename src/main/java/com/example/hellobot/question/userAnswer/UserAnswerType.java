package com.example.hellobot.question.userAnswer;

import com.example.hellobot.common.dataType.BaseEnumCode;

/**
 * @Author Ryu
 * @create 2020/10/18 2:39 오후
 */
public enum UserAnswerType implements BaseEnumCode<String> {
    EVAL_SKILL("evaluateSkill"), TAROT("tarot"), TEXT("text"), SELECT("select");

    private final String userAnswerType;

    UserAnswerType(String uat) {this.userAnswerType = uat;}


    @Override
    public String toString() {
        return this.userAnswerType;
    }

    @Override
    public String getValue() {
        return this.userAnswerType;
    }
}
