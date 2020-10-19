package com.example.hellobot.common.dataType;

import com.example.hellobot.common.dataType.yn.YN;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Author Ryu
 * @create 2020/10/18 5:30 오후
 */

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ActiveDto {
    @ApiModelProperty(value = "활성 여부", required = true)
    private YN active;
}
