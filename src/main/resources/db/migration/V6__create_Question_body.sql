drop table if exists `QuestionBody`;

CREATE TABLE QuestionBody (
    id BIGINT NOT NULL AUTO_INCREMENT,
    questionId BIGINT NOT NULL,
    chatMessageBody JSON NOT NULL,
    typingSpeed DOUBLE DEFAULT 1,
    createdAt DATETIME NOT NULL DEFAULT NOW(),
    updatedAt DATETIME NOT NULL DEFAULT NOW(),
    PRIMARY KEY(id),
    INDEX `idx_id`(id),
    INDEX `idx_question_id`(questionId)
);