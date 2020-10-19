package com.example.hellobot.question.questionBody;

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
 * @create 2020/10/19 9:54 오후
 */
@Slf4j
@Converter(autoApply = true)
public class QuestionBodyJsonConverter implements AttributeConverter<QuestionBodyJson, String> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(QuestionBodyJson attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            log.error("fail to serialize as object into Json : {}", attribute, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public QuestionBodyJson convertToEntityAttribute(String dbData) {
        log.info(dbData);
        try{
            return objectMapper.readValue(dbData, QuestionBodyJson.class);
        }catch (Exception e){
            log.error("fail to deserialize as Json into object : {}", dbData, e);
            throw new RuntimeException(e);
        }
    }
}
