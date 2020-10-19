ALTER TABLE `hello_bot`.`ChatMessage`
ADD COLUMN `contentsId` BIGINT NOT NULL AFTER `botId`,
ADD COLUMN `chatRoomId` BIGINT NOT NULL AFTER `id`;