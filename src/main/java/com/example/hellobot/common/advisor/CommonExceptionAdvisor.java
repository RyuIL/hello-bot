package com.example.hellobot.common.advisor;

import com.example.hellobot.common.exception.DataNotFoundException;
import com.example.hellobot.common.dataType.SimpleMessageDto;
import com.example.hellobot.common.exception.DuplicateChatRoomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.nio.file.AccessDeniedException;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class CommonExceptionAdvisor {

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public SimpleMessageDto handleDataNotFoundException(DataNotFoundException e) {
        log.error("DataNotFoundException", e);
        return SimpleMessageDto.builder()
                .status(404)
                .message("not found")
                .build();
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public SimpleMessageDto handleAccessDeniedException(AccessDeniedException e) {
        log.error("DataNotFoundException", e);
        return SimpleMessageDto.builder()
                .status(403)
                .message("forbidden")
                .build();
    }

    @ExceptionHandler(DuplicateChatRoomException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public SimpleMessageDto handleDuplecateChatRoomException(DuplicateChatRoomException e) {
        log.error("DuplicateChatRoomException", e);
        return SimpleMessageDto.builder()
                .status(400)
                .message("duplicate chat room")
                .build();
    }
}
