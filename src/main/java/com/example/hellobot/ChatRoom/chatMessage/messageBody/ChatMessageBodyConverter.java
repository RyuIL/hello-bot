package com.example.hellobot.chatRoom.chatMessage.messageBody;

import com.example.hellobot.common.dataType.GenericJsonConverter;
import com.example.hellobot.question.userAnswer.Emoji;
import com.example.hellobot.question.userAnswer.UserAnswerBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @Author Ryu
 * @create 2020/10/16 5:46 오후
 */

@Slf4j
@Converter(autoApply = true)
public class ChatMessageBodyConverter implements AttributeConverter<ChatMessageBody, String> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.registerSubtypes(new NamedType(ChatMessageBody.Image.class, "IMAGE"));
        objectMapper.registerSubtypes(new NamedType(ChatMessageBody.Text.class, "TEXT"));
        objectMapper.registerSubtypes(new NamedType(ChatMessageBody.Link.class, "LINK"));
    }

    @Override
    public String convertToDatabaseColumn(ChatMessageBody attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            log.error("fail to serialize as object into Json : {}", attribute, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public ChatMessageBody convertToEntityAttribute(String dbData) {
        log.info(dbData);
        try{
            return objectMapper.readValue(dbData, ChatMessageBody.class);
        }catch (Exception e){
            log.error("fail to deserialize as Json into object : {}", dbData, e);
            throw new RuntimeException(e);
        }
    }
}
