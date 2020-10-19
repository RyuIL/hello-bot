package com.example.hellobot.account;

import com.example.hellobot.common.dataType.yn.YN;
import com.example.hellobot.common.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.nio.file.AccessDeniedException;


/**
 * @Author Ryu
 * @create 2020/10/15 9:17 오후
 */

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountDto.Res login(){
        String ip = this.getMacIpAddress();

        if(ip==null){
            // @TODO exception
        }

        Account account = this.accountRepository.findByIpAddress(ip)
                .orElseGet(() -> this.accountRepository.save(Account.builder().ipAddress(ip).build()));

        return account.toDto();
    }

    public AccountDto.Res updateName(long id, String name){
        Account account = this.accountRepository.findById(id).orElseThrow(DataNotFoundException::new);
        account.updateName(name);
        this.accountRepository.save(account);

        return account.toDto();
    }

    public AccountDto.Res updateActive(long id, YN active){
        Account account = this.accountRepository.findById(id).orElseThrow(DataNotFoundException::new);
        account.updateActive(active);
        return account.toDto();
    }

    public boolean checkAdmin(long id) throws AccessDeniedException {
        if(!this.getRole(id).equals("admin")){
            throw new AccessDeniedException(id+"");
        }
        return true;
    }

    public String getRole(long id){
        // @TODO Authorization 구현
        final String ADMIN = "admin";

        return ADMIN;
    }

    private String getMacIpAddress(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null) {
            ip = request.getHeader("Proxy-Client-IP");
            log.info(">>>> Proxy-Client-IP : " + ip);
        }
        if (ip == null) {
            ip = request.getHeader("WL-Proxy-Client-IP"); // 웹로직
            log.info(">>>> WL-Proxy-Client-IP : " + ip);
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_CLIENT_IP");
            log.info(">>>> HTTP_CLIENT_IP : " + ip);
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            log.info(">>>> HTTP_X_FORWARDED_FOR : " + ip);
        }
        if (ip == null) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }
}
