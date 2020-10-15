package com.example.hellobot.Account;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Ryu
 * @create 2020/10/15 9:17 오후
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
}
