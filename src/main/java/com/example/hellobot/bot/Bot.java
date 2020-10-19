package com.example.hellobot.bot;

import com.example.hellobot.common.dataType.yn.YN;
import com.example.hellobot.common.dataType.yn.YNConverter;
import com.example.hellobot.contents.Contents;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author Ryu
 * @create 2020/10/15 9:32 오후
 */

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bot {

    @Builder
    public Bot(Long id ,String name, String displayName, String description, String profileImage, YN active){
        this.id = id;
        this.name = name;
        this.displayName = displayName;
        this.description = description;
        this.profileImage = profileImage;
        this.active = active;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String displayName;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String profileImage;

    @Convert(converter = YNConverter.class)
    private YN active = YN.Y;

    @OneToMany(mappedBy = "bot", orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Contents> contents = new HashSet<>();

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    protected void update(BotDto.CreationRequest req){
        this.name = req.getName();
        this.displayName = req.getDisplayName();
        this.description = req.getDescription();
        this.profileImage = req.getProfileImage();
    }

    protected void updateActive(YN active) {
        this.active = active;
    }
}
