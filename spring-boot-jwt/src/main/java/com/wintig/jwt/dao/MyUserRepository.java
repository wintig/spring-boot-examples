package com.wintig.jwt.dao;

import com.wintig.jwt.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description
 * @Author wintig
 * @Create 2018-10-21 下午10:33
 */
public interface MyUserRepository  extends JpaRepository<MyUser, Long> {

    MyUser findByUsername(String username);


}