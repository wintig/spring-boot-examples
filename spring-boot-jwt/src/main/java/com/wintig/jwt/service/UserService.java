package com.wintig.jwt.service;


import com.wintig.jwt.dao.MyUserRepository;
import com.wintig.jwt.entity.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private MyUserRepository myUserRepository;

    public MyUser save(MyUser user) {
        return myUserRepository.save(user);
    }

}
