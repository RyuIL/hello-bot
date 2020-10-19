package com.example.hellobot.account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @Author Ryu
 * @create 2020/10/15 9:17 오후
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByIpAddress(String ip);
}
