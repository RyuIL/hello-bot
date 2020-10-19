package com.example.hellobot.account;

import com.example.hellobot.common.dataType.ActiveDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/login")
    @ApiOperation(value = "로그인", notes = "ip로 임시 로그인 구현")
    AccountDto.Res login() {
        return accountService.login();
    }

    @PutMapping("/name/{id}")
    @ApiOperation(value = "이름 변경")
    AccountDto.Res updateName(
            @PathVariable("id") @ApiParam(value = "id") long id,
            @RequestBody @ApiParam(value = "유저 이름") AccountDto.UpdateName userName
    ) {
        return accountService.updateName(id, userName.getName());
    }

    @PutMapping("/{id}/active")
    @ApiOperation(value = "활성화 업데이트")
    AccountDto.Res updateActive(
            @PathVariable("id") @ApiParam(value = "id") long id,
            @RequestBody @ApiParam(value = "유저 활성화 여부") ActiveDto active
    ) throws AccessDeniedException {
        accountService.checkAdmin(id);
        return accountService.updateActive(id, active.getActive());
    }
}
