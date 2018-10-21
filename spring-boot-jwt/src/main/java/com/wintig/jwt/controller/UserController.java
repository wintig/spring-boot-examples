package com.wintig.jwt.controller;

import com.wintig.jwt.entity.MyUser;
import com.wintig.jwt.entity.Response;
import com.wintig.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public Response<?> hello() {
        return Response.success("hello");
    }

    @PostMapping("/register")
    public Response<MyUser> register(@RequestBody MyUser user) {
        return Response.success(userService.save(user));
    }



}
