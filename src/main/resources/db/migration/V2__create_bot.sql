drop table if exists `Bot`;

CREATE TABLE Bot (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    displayName VARCHAR(100) NOT NULL,
    description TEXT,
    profileImage TEXT,
    active VARCHAR(1) NOT NULL DEFAULT 'Y',
    createdAt DATETIME NOT NULL DEFAULT NOW(),
    updatedAt DATETIME NOT NULL DEFAULT NOW(),
    PRIMARY KEY(id),
    INDEX `idx_id`(id)
);