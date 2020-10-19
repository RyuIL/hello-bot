package com.example.hellobot.question;

import com.example.hellobot.account.AccountService;
import com.example.hellobot.common.dataType.SimpleMessageDto;
import com.example.hellobot.question.questionBody.QuestionBodyDto;
import com.example.hellobot.question.questionBody.QuestionBodyService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

/**
 * @Author Ryu
 * @create 2020/10/18 4:33 오후
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionBodyService questionBodyService;
    private final AccountService accountService;

    @PutMapping("/text-answer")
    @ApiOperation(value = "질문, 답변 텍스트 생성")
    QuestionDto.Res creationQuestionAndUserAnswerTextBody(
            @RequestBody @ApiParam("생성 req") QuestionDto.UserAnswerTextBodyCreationReq req,
            @RequestHeader("auth-key") @ApiParam(value = "user key", required = true) long authKey
    ) throws AccessDeniedException {
        accountService.checkAdmin(authKey);
        return this.questionService.upsertUserAnswerTextBody(req);
    }

    @PutMapping("/tarot-answer")
    @ApiOperation(value = "질문, 답변 타로 생성", notes = "status : START, ING ,COMPLETE <br /> type: tarot, text")
    QuestionDto.Res creationQuestionAndUserAnswerTarotBody(
            @RequestBody @ApiParam("생성 req") QuestionDto.UserAnswerTarotBodyCreationReq req,
            @RequestHeader("auth-key") @ApiParam(value = "user key", required = true) long authKey
    ) throws AccessDeniedException {
        accountService.checkAdmin(authKey);
        return this.questionService.upsertUserAnswerTarotBody(req);
    }

    @PutMapping("/evaluateSkill")
    @ApiOperation(value = "질문, 답변 평가 생성")
    QuestionDto.Res creationQuestionAndUserAnswerEvaluateSkillBody(
            @RequestBody @ApiParam("생성 req") QuestionDto.UserAnswerEvaluateSkillBodyCreationReq req,
            @RequestHeader("auth-key") @ApiParam(value = "user key", required = true) long authKey
    ) throws AccessDeniedException {
        accountService.checkAdmin(authKey);
        return this.questionService.upsertUserAnswerEvaluateSkillBody(req);
    }

    @PutMapping("/select")
    @ApiOperation(value = "질문, 답변 선택지 생성", notes = "status : START, ING ,COMPLETE <br /> type: tarot, text")
    QuestionDto.Res creationQuestionAndUserAnswerSelectBody(
            @RequestBody @ApiParam("생성 req") QuestionDto.UserAnswerSelectBodyCreationReq req,
            @RequestHeader("auth-key") @ApiParam(value = "user key", required = true) long authKey
    ) throws AccessDeniedException {
        accountService.checkAdmin(authKey);
        return this.questionService.upsertUserAnswerSelectBody(req);
    }

    @PutMapping("/body")
    QuestionBodyDto.Res creationQuestionBody(
            @RequestBody @ApiParam("생성 req") QuestionBodyDto.CreationRequest req,
            @RequestHeader("auth-key") @ApiParam(value = "user key", required = true) long authKey
    ) throws AccessDeniedException{
        accountService.checkAdmin(authKey);
        return this.questionBodyService.upsertQuestionBody(req);
    }

    @GetMapping("/{id}")
    QuestionDto.Res getById(
            @PathVariable("id") @ApiParam("id") Long questionId
    ){
        return this.questionService.getById(questionId);
    }

    @GetMapping("/first-question/{contentsId}")
    QuestionDto.Res getFirstQuestionByContentsId(
            @PathVariable("contentsId") @ApiParam("id") Long questionId
    ){
        return this.questionService.getFirstQuestion(questionId);
    }

    @DeleteMapping("/{id}")
    SimpleMessageDto deleteByID(
            @PathVariable("id") @ApiParam("id") Long questionId,
            @RequestHeader("auth-key") @ApiParam(value = "user key", required = true) long authKey
    ) throws AccessDeniedException {
        accountService.checkAdmin(authKey);
        return this.questionService.deleteQuestion(questionId);
    }
}
