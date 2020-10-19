package com.example.hellobot.question;

import com.example.hellobot.common.exception.DataNotFoundException;
import com.example.hellobot.common.dataType.SimpleMessageDto;
import com.example.hellobot.question.userAnswer.UserAnswerBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Author Ryu
 * @create 2020/10/18 4:34 오후
 */

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionDto.Res getById(long id){
        return this.questionRepository.findById(id).orElseThrow(DataNotFoundException::new).toDto();
    }

    public QuestionDto.Res upsertUserAnswerTextBody(QuestionDto.UserAnswerTextBodyCreationReq req){
        if(req.getId() != 0 && req.getId()!=null){
            Question question = this.questionRepository.findById(req.getId()).orElseThrow(DataNotFoundException::new);
            question.updateByUserAnswerTextBodyDto(req);
            return this.questionRepository.save(question).toDto();
        }else {
            Question question = req.toEntity();
            UserAnswerBody.Text text = (UserAnswerBody.Text) question.getUserAnswerBody();
            this.questionRepository.save(question);
            return question.toDto();
        }
    }

    public QuestionDto.Res upsertUserAnswerTarotBody(QuestionDto.UserAnswerTarotBodyCreationReq req){
        if(req.getId() != 0 && req.getId()!=null){
            Question question = this.questionRepository.findById(req.getId()).orElseThrow(DataNotFoundException::new);
            question.updateByUserAnswerTarotBodyDto(req);
            return this.questionRepository.save(question).toDto();
        }else {
            return this.questionRepository.save(req.toEntity()).toDto();
        }
    }

    public QuestionDto.Res upsertUserAnswerSelectBody(QuestionDto.UserAnswerSelectBodyCreationReq req){
        if(req.getId() != 0 && req.getId()!=null){
            Question question = this.questionRepository.findById(req.getId()).orElseThrow(DataNotFoundException::new);
            question.updateByUserAnswerSelectBodyDto(req);
            return this.questionRepository.save(question).toDto();
        }else {
            return this.questionRepository.save(req.toEntity()).toDto();
        }
    }

    public QuestionDto.Res upsertUserAnswerEvaluateSkillBody(QuestionDto.UserAnswerEvaluateSkillBodyCreationReq req){
        if(req.getId() != 0 && req.getId()!=null){
            Question question = this.questionRepository.findById(req.getId()).orElseThrow(DataNotFoundException::new);
            question.updateByUserAnswerEvaluateSkillBodyDto(req);
            return this.questionRepository.save(question).toDto();
        }else {
            return this.questionRepository.save(req.toEntity()).toDto();
        }
    }

    public SimpleMessageDto deleteQuestion(long id){
        Question question = this.questionRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.questionRepository.delete(question);
        return SimpleMessageDto.builder().message("ok").status(200).build();
    }

    public QuestionDto.Res getFirstQuestion(long id){
        return this.questionRepository.findByContentsIdAndStatus(id, QuestionStatusType.START).orElseThrow(DataNotFoundException::new).toDto();
    }
}
