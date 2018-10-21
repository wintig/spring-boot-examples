package com.wintig.jwt.filter;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wintig.jwt.entity.MyUser;
import com.wintig.jwt.entity.Response;
import com.wintig.jwt.utils.DateUtils;
import com.wintig.jwt.utils.ResponseUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * 登陆请求filter，用来过滤/login 请求并处理。
 */
public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {

            //获取前端过来的user信息
            MyUser myUser = new ObjectMapper()
                    .readValue(req.getInputStream(), MyUser.class);


            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            myUser.getUsername(),
                            myUser.getPassword(),
                            new ArrayList<>())
            );

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 请求校验成功后的处理
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) {

        HashMap<String, String> result = new HashMap<>();

        String token = Jwts.builder()
                .setSubject(((User) auth.getPrincipal()).getUsername())
                .setIssuedAt(new Date())
                .setExpiration(DateUtils.LocalDateTimeToDate(LocalDateTime.now().plusDays(7))) //设置有效期7天
                .signWith(SignatureAlgorithm.HS512, "jwtSecurity")
                .compact();

        result.put("Authorization", "Bearer " + token);

        //将生成token返回给前台
        ResponseUtil.write(res, JSON.toJSON(Response.success(result)));
    }

}
