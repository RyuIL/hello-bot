package com.example.hellobot.question;

import com.example.hellobot.contents.Contents;
import com.example.hellobot.question.questionBody.QuestionBody;
import com.example.hellobot.question.userAnswer.UserAnswerBody;
import com.example.hellobot.question.userAnswer.UserAnswerBodyConverter;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author Ryu
 * @create 2020/10/15 11:54 오후
 */

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question {

    @Builder
    public Question(long id, Contents contents, UserAnswerBody userAnswerBody, QuestionStatusType status) {
        this.id = id;
        this.contents = contents;
        this.userAnswerBody = userAnswerBody;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = QuestionStatusTypeConverter.class)
    private QuestionStatusType status ;

    @ManyToOne
    @JoinColumn(name = "contentsId", updatable = false)
    private Contents contents;

    @OneToMany(mappedBy = "question", orphanRemoval = true ,fetch = FetchType.EAGER)
    private Set<QuestionBody> questionBodies = new HashSet<>();

    @Column(columnDefinition = "JSON")
    @Convert(converter = UserAnswerBodyConverter.class)
    private UserAnswerBody userAnswerBody;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public QuestionDto.Res toDto(){
        return QuestionDto.Res.builder()
                .id(this.id)
                .contentsId(this.contents.getId())
                .status(this.status)
                .questionBodies(this.questionBodies
                        .stream()
                        .sorted(Comparator.comparing(QuestionBody::getId))
                        .map(QuestionBody::toDto)
                        .collect(Collectors.toList())
                )
                .userAnswerBody(this.userAnswerBody)
                .build();
    }

    public void updateByUserAnswerTextBodyDto(QuestionDto.UserAnswerTextBodyCreationReq req){
        this.contents = Contents.builder().id(req.getContentsId()).build();
        this.userAnswerBody = req.getUserAnswerBody();
    }

    public void updateByUserAnswerTarotBodyDto(QuestionDto.UserAnswerTarotBodyCreationReq req){
        this.contents = Contents.builder().id(req.getContentsId()).build();
        this.userAnswerBody = req.getUserAnswerBody();
    }

    public void updateByUserAnswerEvaluateSkillBodyDto(QuestionDto.UserAnswerEvaluateSkillBodyCreationReq req){
        this.contents = Contents.builder().id(req.getContentsId()).build();
        this.userAnswerBody = req.getUserAnswerBody();
    }

    public void updateByUserAnswerSelectBodyDto(QuestionDto.UserAnswerSelectBodyCreationReq req){
        this.contents = Contents.builder().id(req.getContentsId()).build();
        this.userAnswerBody = req.getUserAnswerBody();
    }

}
