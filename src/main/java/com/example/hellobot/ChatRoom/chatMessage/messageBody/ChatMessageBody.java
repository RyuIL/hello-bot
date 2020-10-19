package com.example.hellobot.chatRoom.chatMessage.messageBody;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import javax.persistence.Convert;
import java.time.LocalDateTime;

/**
 * @Author Ryu
 * @create 2020/10/16 5:19 오후
 */

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ChatMessageBody.Image.class, name = "image"),
        @JsonSubTypes.Type(value = ChatMessageBody.Text.class, name= "text"),
        @JsonSubTypes.Type(value = ChatMessageBody.Link.class, name= "link")
})
public abstract class ChatMessageBody {

    @Data
    public static class Image extends ChatMessageBody {
        private Integer height;
        private Integer width;
        private String imageUrl;
    }

    @Data
    public static class Text extends ChatMessageBody {
        private String text;
    }

    @Data
    public static class Link extends ChatMessageBody {
        private String link;
    }

    @Convert(converter = ChatMessageBodyConverter.class)
    private ChatMessageBodyType type;
}
