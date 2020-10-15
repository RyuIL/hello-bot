drop table if exists `Account`;

CREATE TABLE Account (
    id BIGINT NOT NULL AUTO_INCREMENT,
    ipAddress VARCHAR(100),
    macAddress VARCHAR(100),
    createdAt DATETIME NOT NULL DEFAULT NOW(),
    updatedAt DATETIME NOT NULL DEFAULT NOW(),
    PRIMARY KEY(id),
    INDEX `idx_id`(id)
);