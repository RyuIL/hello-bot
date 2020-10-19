package com.example.hellobot.question.questionBody;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Author Ryu
 * @create 2020/10/18 4:54 오후
 */

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionBodyService {
    private final QuestionBodyRepository questionBodyRepository;

    public QuestionBodyDto.Res upsertQuestionBody(QuestionBodyDto.CreationRequest req){
        return this.questionBodyRepository.save(req.toEntity()).toDto();
    }
}
