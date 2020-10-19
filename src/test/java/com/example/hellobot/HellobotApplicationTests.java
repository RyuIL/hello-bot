package com.example.hellobot;

import com.example.hellobot.account.AccountDto;
import com.example.hellobot.account.AccountService;
import com.example.hellobot.bot.Bot;
import com.example.hellobot.bot.BotDto;
import com.example.hellobot.bot.BotRepository;
import com.example.hellobot.bot.BotService;
import com.example.hellobot.chatRoom.chatMessage.messageBody.ChatMessageBodyType;
import com.example.hellobot.common.dataType.yn.YN;
import com.example.hellobot.common.exception.DataNotFoundException;
import com.example.hellobot.contents.*;
import com.example.hellobot.question.*;
import com.example.hellobot.question.questionBody.*;
import com.example.hellobot.question.userAnswer.Emoji;
import com.example.hellobot.question.userAnswer.UserAnswerBody;
import com.example.hellobot.question.userAnswer.UserAnswerType;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import javax.transaction.Transactional;
import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)
class HellobotApplicationTests {

    @Autowired
    AccountService accountService;
    @Autowired
    BotService botService;
    @Autowired
    BotRepository botRepository;
    @Autowired
    ContentsService contentsService;
    @Autowired
    ContentsRepository contentsRepository;
    @Autowired
    QuestionService questionService;
    @Autowired
    QuestionBodyRepository questionBodyRepository;
    @Autowired
    QuestionBodyService questionBodyService;
    @Autowired
    QuestionRepository questionRepository;

    @Test
    @Transactional
    void contentsTarotTest(){
        //login
        AccountDto.Res account = accountService.login();
        assertNotEquals(account.getId(), 0);
        String role = accountService.getRole(account.getId());
        assertEquals("admin", role);

        //create bot
        Bot bot = botRepository.save(
                Bot.builder()
                        .profileImage("http://example.com")
                        .description("bot1 description")
                        .name("bot1")
                        .displayName("bot1 display name")
                        .active(YN.Y)
                        .build()
        );
        BotDto.Res botDto = botService.getById(bot.getId());
        assertNotEquals(botDto.getId(), 0);

        // create tarot contents
        Contents contents = contentsRepository.save(
                Contents.builder()
                        .type(ContentsType.TAROT)
                        .displayName("contents dn")
                        .name("contents1")
                        .description("contents1 description")
                        .botId(bot.getId())
                        .build()
        );
        ContentsDto.Res contentsDto = contentsService.getById(contents.getId()).orElseThrow(DataNotFoundException::new);
        assertNotEquals(contentsDto.getBotId(), 0);


        // create questions
        // TEXT -> 선택지 -> 타로

        UserAnswerBody.Text userAnswer1 = new UserAnswerBody.Text();
        userAnswer1.setType(UserAnswerType.TEXT);
        userAnswer1.setNextQuestionId(2L);

        Question question = questionRepository.save(
                Question.builder()
                        .status(QuestionStatusType.START)
                        .contents(contents)
                        .userAnswerBody(userAnswer1)
                        .build()
        );



        List<UserAnswerBody.Select.SelectBody> selectBodyList = Lists.newArrayList();

        UserAnswerBody.Select.SelectBody select1 = new UserAnswerBody.Select.SelectBody();
        select1.setNextQuestionId(3L);
        select1.setText("선택지1");
        selectBodyList.add(select1);

        UserAnswerBody.Select.SelectBody select2 = new UserAnswerBody.Select.SelectBody();
        select1.setNextQuestionId(4L);
        select1.setText("선택지2");
        selectBodyList.add(select2);

        UserAnswerBody.Select userAnswer2 = new UserAnswerBody.Select();
        userAnswer2.setChoiceList(selectBodyList);
        userAnswer2.setPickCount(1);
        userAnswer2.setTotalCount(selectBodyList.size());
        userAnswer2.setType(UserAnswerType.SELECT);


        Question question2 = questionRepository.save(
                Question.builder()
                        .status(QuestionStatusType.ING)
                        .contents(contents)
                        .userAnswerBody(userAnswer2)
                        .build()
        );


        UserAnswerBody.Tarot userAnswer3 = new UserAnswerBody.Tarot();
        userAnswer3.setNextQuestionId(5L);
        userAnswer3.setPickCount(1);
        userAnswer3.setTotalCount(5);
        userAnswer3.setTarotBackImage("image");
        userAnswer3.setTarotFrontImage("image");
        userAnswer3.setType(UserAnswerType.TAROT);

        Question question3 = questionRepository.save(
                Question.builder()
                        .status(QuestionStatusType.ING)
                        .contents(contents)
                        .userAnswerBody(userAnswer3)
                        .build()
        );


        UserAnswerBody.Select userAnswer4 = new UserAnswerBody.Select();
        selectBodyList.forEach(selectBody -> selectBody.setNextQuestionId(0L));
        userAnswer4.setChoiceList(selectBodyList);
        userAnswer4.setPickCount(1);
        userAnswer4.setTotalCount(selectBodyList.size());
        userAnswer4.setType(UserAnswerType.SELECT);


        Question question4 = questionRepository.save(
                Question.builder()
                        .status(QuestionStatusType.COMPLETE)
                        .contents(contents)
                        .userAnswerBody(userAnswer2)
                        .build()
        );

        assertNotEquals(question.getId(), 0);
        assertNotEquals(question2.getId(), 0);
        assertNotEquals(question3.getId(), 0);
        assertNotEquals(question4.getId(), 0);

        //create question body

        QuestionBodyJson json = new QuestionBodyJson();
        json.setData("test");
        json.setType(ChatMessageBodyType.TEXT);

        questionBodyRepository.save(
                QuestionBody.builder()
                .chatMessageBody(json)
                .question(question)
                .typingSpeed(1.0)
                .build()
        );

        json = new QuestionBodyJson();
        json.setData("test2");
        json.setType(ChatMessageBodyType.TEXT);

        questionBodyRepository.save(
                QuestionBody.builder()
                        .chatMessageBody(json)
                        .question(question2)
                        .typingSpeed(1.0)
                        .build()
        );
        json = new QuestionBodyJson();
        json.setData("test3");
        json.setType(ChatMessageBodyType.TEXT);

        questionBodyRepository.save(
                QuestionBody.builder()
                        .chatMessageBody(json)
                        .question(question2)
                        .typingSpeed(1.0)
                        .build()
        );


        json = new QuestionBodyJson();
        json.setData("test4");
        json.setType(ChatMessageBodyType.IMAGE);

        questionBodyRepository.save(
                QuestionBody.builder()
                        .chatMessageBody(json)
                        .question(question3)
                        .typingSpeed(1.0)
                        .build()
        );
        json = new QuestionBodyJson();
        json.setData("test5");
        json.setType(ChatMessageBodyType.IMAGE);

        questionBodyRepository.save(
                QuestionBody.builder()
                        .chatMessageBody(json)
                        .question(question3)
                        .typingSpeed(1.0)
                        .build()
        );
        json = new QuestionBodyJson();
        json.setData("test6");
        json.setType(ChatMessageBodyType.IMAGE);

        questionBodyRepository.save(
                QuestionBody.builder()
                        .chatMessageBody(json)
                        .question(question3)
                        .typingSpeed(1.0)
                        .build()
        );
        json = new QuestionBodyJson();
        json.setData("test7");
        json.setType(ChatMessageBodyType.IMAGE);

        questionBodyRepository.save(
                QuestionBody.builder()
                        .chatMessageBody(json)
                        .question(question3)
                        .typingSpeed(1.0)
                        .build()
        );
        json = new QuestionBodyJson();
        json.setData("test8");
        json.setType(ChatMessageBodyType.IMAGE);

        questionBodyRepository.save(
                QuestionBody.builder()
                        .chatMessageBody(json)
                        .question(question3)
                        .typingSpeed(1.0)
                        .build()
        );


        json = new QuestionBodyJson();
        json.setData("test9");
        json.setType(ChatMessageBodyType.TEXT);

        questionBodyRepository.save(
                QuestionBody.builder()
                        .chatMessageBody(json)
                        .question(question4)
                        .typingSpeed(1.0)
                        .build()
        );
        json = new QuestionBodyJson();
        json.setData("test10");
        json.setType(ChatMessageBodyType.TEXT);

        questionBodyRepository.save(
                QuestionBody.builder()
                        .chatMessageBody(json)
                        .question(question4)
                        .typingSpeed(1.0)
                        .build()
        );
    }

}
