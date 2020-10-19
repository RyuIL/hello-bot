ALTER TABLE `hello_bot`.`ChatMessage`
ADD COLUMN `pickQuestionId` BIGINT NOT NULL AFTER `botId`;