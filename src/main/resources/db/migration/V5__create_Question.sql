drop table if exists `Question`;

CREATE TABLE Question (
    id BIGINT NOT NULL AUTO_INCREMENT,
    contentsId BIGINT NOT NULL,
    userAnswerBody JSON NOT NULL,
    createdAt DATETIME NOT NULL DEFAULT NOW(),
    updatedAt DATETIME NOT NULL DEFAULT NOW(),
    PRIMARY KEY(id),
    INDEX `idx_id`(id),
    INDEX `idx_contents_id`(contentsId)
);