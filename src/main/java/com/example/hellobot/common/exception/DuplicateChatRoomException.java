package com.example.hellobot.common.exception;

/**
 * @Author Ryu
 * @create 2020/10/19 6:24 오후
 */
public class DuplicateChatRoomException extends RuntimeException{
    public DuplicateChatRoomException() {
    }
    public DuplicateChatRoomException(String message) {
        super(message);
    }
}
