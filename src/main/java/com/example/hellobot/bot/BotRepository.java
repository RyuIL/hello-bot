package com.example.hellobot.bot;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Ryu
 * @create 2020/10/15 11:32 오후
 */
public interface BotRepository extends JpaRepository<Bot, Long> {
}
