package com.example.hellobot.contents;

import com.example.hellobot.bot.Bot;
import com.example.hellobot.common.dataType.yn.YN;
import com.example.hellobot.common.dataType.yn.YNConverter;
import com.example.hellobot.question.Question;
import com.google.common.collect.Sets;
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
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author Ryu
 * @create 2020/10/15 11:34 오후
 */

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Contents {

    @Builder
    public Contents(Long id, Long botId, ContentsType type, String name, String displayName, String description) {
        this.id = id;
        this.bot = Bot.builder().id(botId).build();
        this.type = type;
        this.name = name;
        this.displayName = displayName;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "botId", updatable = false)
    private Bot bot;

    @OneToMany(mappedBy = "contents", orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Question> questions = Sets.newHashSet();

    @Convert(converter = ContentsTypeConverter.class)
    private ContentsType type = ContentsType.TAROT;

    private String name;

    private String displayName;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Convert(converter = YNConverter.class)
    private YN active = YN.Y;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public void setId(long id){
        this.id = id;
    }

    public void updateActive(YN active){
        this.active = active;
    }

    public void updateByDto(ContentsDto.CreationReq req){
        this.bot = Bot.builder().id(req.getBotId()).build();
        this.name = req.getName();
        this.displayName = req.getDisplayName();
        this.description = req.getDescription();
    }


    public ContentsDto.Res toDto(){
        return ContentsDto.Res.builder()
                .id(this.id)
                .botId(this.bot.getId())
                .description(this.description)
                .displayName(this.displayName)
                .questions(this.questions
                        .stream()
                        .sorted(Comparator.comparing(Question::getId))
                        .map(Question::toDto)
                        .collect(Collectors.toList()))
                .name(this.name)
                .type(this.type)
                .build();
    }
}
