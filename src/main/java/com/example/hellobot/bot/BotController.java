package com.example.hellobot.bot;

import com.example.hellobot.account.AccountService;
import com.example.hellobot.common.dataType.ActiveDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

/**
 * @Author Ryu
 * @create 2020/10/17 11:27 오후
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/bot")
public class BotController {

    private final BotService botService;
    private final AccountService accountService;

    @PutMapping
    @ApiOperation(value = "생성 / 업데이트")
    public BotDto.Res upsertBot(
            @RequestBody @ApiParam(value = "생성 request") BotDto.CreationRequest req
    ) {
        return botService.createBot(req);
    }

    @PutMapping("/{id}/active")
    @ApiOperation(value = "활성 여부 업데이트")
    public BotDto.Res updateActive(
            @PathVariable @ApiParam(value = "id") long id,
            @RequestBody @ApiParam(value = "활성화 여부") ActiveDto active
    ) throws AccessDeniedException {
        if(!accountService.getRole(id).equals("admin")){
            throw new AccessDeniedException(String.valueOf(id));
        }
        return botService.updateActive(id, active.getActive());
    }

}
