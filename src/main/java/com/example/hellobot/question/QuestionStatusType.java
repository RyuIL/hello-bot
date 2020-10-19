package com.example.hellobot.question;

import com.example.hellobot.common.dataType.BaseEnumCode;

/**
 * @Author Ryu
 * @create 2020/10/19 5:32 오후
 */
public enum QuestionStatusType implements BaseEnumCode<String> {
    START("start"), ING("ing"), COMPLETE("complete");

    private final String status;

    QuestionStatusType(String status) {this.status = status;}


    @Override
    public String getValue() {
        return this.status;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
