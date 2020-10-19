package com.example.hellobot.chatRoom;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @Author Ryu
 * @create 2020/10/19 5:47 오후
 */
public interface ChatRoomRepository extends CrudRepository<ChatRoom, Long> {

    @Query("select cr from ChatRoom cr " +
           "where cr.accountId=:accountId and cr.botId=:botId")
    Optional<ChatRoom> findByAccountIdAndBotId(@Param("accountId")long accountId, @Param("botId")long botId);
}
