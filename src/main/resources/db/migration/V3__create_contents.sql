drop table if exists `Contents`;

CREATE TABLE Contents (
    id BIGINT NOT NULL AUTO_INCREMENT,
    botId BIGINT NOt NULL,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(100) NOT NULL,
    displayName VARCHAR(100) NOT NULL,
    description TEXT,
    active VARCHAR(1) NOT NULL DEFAULT 'Y',
    createdAt DATETIME NOT NULL DEFAULT NOW(),
    updatedAt DATETIME NOT NULL DEFAULT NOW(),
    PRIMARY KEY(id),
    INDEX `idx_id`(id),
    INDEX `idx_bot_id`(botId)
);