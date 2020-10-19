package com.example.hellobot.contents;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author Ryu
 * @create 2020/10/18 4:05 오후
 */
public interface ContentsRepository extends CrudRepository<Contents, Long> {
    List<Contents> findAll();
}
