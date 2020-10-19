package com.example.hellobot.common.dataType;

import com.example.hellobot.question.userAnswer.UserAnswerBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.lang.reflect.Type;

@Slf4j
public class GenericJsonConverter<T> implements AttributeConverter<T, String> {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final Gson gson = new Gson();

    static {
        objectMapper.registerSubtypes(new NamedType(UserAnswerBody.Tarot.class, "tarot"));
        objectMapper.registerSubtypes(new NamedType(UserAnswerBody.EvaluateSkill.class, "evaluateSkill"));
        objectMapper.registerSubtypes(new NamedType(UserAnswerBody.Text.class, "TEXT"));
    }

    @Override
    public String convertToDatabaseColumn(T additionalData) {
        // AdditionalData -> Json문자열로 변환
        try {
            String a = objectMapper.writeValueAsString(additionalData);
            return a;
        } catch (JsonProcessingException e) {
            log.error("fail to serialize as object into Json : {}", additionalData, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public T convertToEntityAttribute(String jsonStr) {
        log.info(jsonStr);
        try{
//            Type collectionType = new TypeToken<T>(){}.getType();
//            return gson.fromJson(jsonStr, collectionType);
            UserAnswerBody.Text b = gson.fromJson(jsonStr, UserAnswerBody.Text.class);
            UserAnswerBody.Text a = objectMapper.readValue(jsonStr, UserAnswerBody.Text.class);
            return objectMapper.readValue(jsonStr, new TypeReference<T>(){});
        }catch (Exception e){
            log.error("fail to deserialize as Json into object : {}", jsonStr, e);
            throw new RuntimeException(e);
        }
    }
}
