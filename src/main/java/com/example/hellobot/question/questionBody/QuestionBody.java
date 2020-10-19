package com.example.hellobot.question.questionBody;

import com.example.hellobot.chatRoom.chatMessage.ChatMessage;
import com.example.hellobot.chatRoom.chatMessage.SenderType;
import com.example.hellobot.question.Question;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @Author Ryu
 * @create 2020/10/18 3:51 오후
 */
@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionBody {

    @Builder
    public QuestionBody(Long id, Question question, Double typingSpeed, QuestionBodyJson chatMessageBody) {
        this.id = id;
        this.question = question;
        this.typingSpeed = typingSpeed;
        this.chatMessageBody = chatMessageBody;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "JSON")
    @Convert(converter = QuestionBodyJsonConverter.class)
    private QuestionBodyJson chatMessageBody;

    @ManyToOne
    @JoinColumn(name = "questionId", updatable = false)
    private Question question;

    private Double typingSpeed;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

//    public ChatMessage toChatMessage(long userId){
//        return ChatMessage.builder()
//                .botId(this.question.getContents().getBot().getId())
//                .chatMessageBody(this.chatMessageBody)
//                .senderId(userId)
//                .senderType(SenderType.BOT)
//                .build();
//    }

    public QuestionBodyDto.Res toDto(){
        return QuestionBodyDto.Res.builder()
                .id(this.id)
                .questionId(this.question.getId())
                .chatMessageBody(this.chatMessageBody)
                .typingSpeed(this.typingSpeed)
                .build();
    }
}
