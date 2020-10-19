drop table if exists `Account`;

CREATE TABLE Account (
    id BIGINT NOT NULL AUTO_INCREMENT,
    ipAddress VARCHAR(100) NOT NULL,
    name VARCHAR(16),
    nickName VARCHAR(16),
    profileImage TEXT,
    active VARCHAR(1) NOT NULL DEFAULT 'Y',
    createdAt DATETIME NOT NULL DEFAULT NOW(),
    updatedAt DATETIME NOT NULL DEFAULT NOW(),
    PRIMARY KEY(id),
    INDEX `idx_id`(id)
);