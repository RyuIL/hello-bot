package com.example.hellobot.question;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @Author Ryu
 * @create 2020/10/18 4:34 오후
 */
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Optional<Question> findByContentsIdAndStatus(long contentsId, QuestionStatusType status);
}
