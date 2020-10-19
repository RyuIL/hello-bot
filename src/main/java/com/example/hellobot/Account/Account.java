package com.example.hellobot.account;

import com.example.hellobot.common.dataType.yn.YN;
import com.example.hellobot.common.dataType.yn.YNConverter;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    @Builder
    public Account(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ipAddress;

    private String name;

    private String nickName;

    @Column(columnDefinition = "TEXT")
    private String profileImage;

    @Convert(converter = YNConverter.class)
    private YN active = YN.Y;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public AccountDto.Res toDto() {
        return AccountDto.Res.builder()
                .id(this.id)
                .ipAddress(this.ipAddress)
                .name(this.name)
                .nickName(this.nickName)
                .profileImage(this.profileImage)
                .active(this.active)
                .build();
    }

    protected void updateName(String name) {
        this.name = name;
    }

    protected void updateActive(YN active){
        this.active = active;
    }
}
