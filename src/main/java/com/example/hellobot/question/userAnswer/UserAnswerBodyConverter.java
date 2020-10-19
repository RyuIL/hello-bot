package com.example.hellobot.question.userAnswer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;

/**
 * @Author Ryu
 * @create 2020/10/18 2:54 오후
 */

@Slf4j
public class UserAnswerBodyConverter implements AttributeConverter<UserAnswerBody, String> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.registerSubtypes(new NamedType(UserAnswerBody.Tarot.class, "TAROT"));
        objectMapper.registerSubtypes(new NamedType(UserAnswerBody.EvaluateSkill.class, "EVAL_SKILL"));
        objectMapper.registerSubtypes(new NamedType(UserAnswerBody.Select.class, "SELECT"));
        objectMapper.registerSubtypes(new NamedType(UserAnswerBody.Text.class, "TEXT"));
        objectMapper.registerSubtypes(new NamedType(Emoji.class));
    }

    @Override
    public String convertToDatabaseColumn(UserAnswerBody attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            log.error("fail to serialize as object into Json : {}", attribute, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserAnswerBody convertToEntityAttribute(String dbData) {
        log.info(dbData);
        try{
//            Type collectionType = new TypeToken<T>(){}.getType();
//            return gson.fromJson(jsonStr, collectionType);
//            UserAnswerBody.Text b = gson.fromJson(jsonStr, UserAnswerBody.Text.class);
            return objectMapper.readValue(dbData, UserAnswerBody.class);
        }catch (Exception e){
            log.error("fail to deserialize as Json into object : {}", dbData, e);
            throw new RuntimeException(e);
        }
    }
}
