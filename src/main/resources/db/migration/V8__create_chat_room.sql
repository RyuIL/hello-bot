drop table if exists `ChatRoom`;

CREATE TABLE ChatRoom (
    id BIGINT NOT NULL AUTO_INCREMENT,
    accountId BIGINT NOT NULL,
    botId BIGINT NOT NULL,
    createdAt DATETIME NOT NULL DEFAULT NOW(),
    updatedAt DATETIME NOT NULL DEFAULT NOW(),
    PRIMARY KEY(id),
    INDEX `idx_id`(id),
    INDEX `idx_account_id`(accountId),
    INDEX `idx_bot_id`(botId)
);