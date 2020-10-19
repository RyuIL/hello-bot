drop table if exists `ChatMessage`;

CREATE TABLE ChatMessage (
    id BIGINT NOT NULL AUTO_INCREMENT,
    senderId BIGINT NOT NULL,
    botId BIGINT NOT NULL,
    senderType VARCHAR(100) NOT NULL,
    typingSpeed DOUBLE DEFAULT 1 NOT NULL,
    chatMessageBody JSON NOT NULL,
    createdAt DATETIME NOT NULL DEFAULT NOW(),
    PRIMARY KEY(id),
    INDEX `idx_id`(id),
    INDEX `idx_bot_id`(botId),
    INDEX `idx_sender_id`(senderId)
);