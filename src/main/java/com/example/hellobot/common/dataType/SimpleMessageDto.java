package com.example.hellobot.common.dataType;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SimpleMessageDto {
    int status;
    String message;
}
