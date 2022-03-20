package com.board.loginboard.controller;
import javax.servlet.http.HttpServletRequest;

import com.board.loginboard.dto.AccountDto;
import com.board.loginboard.dto.SignDto;
import com.board.loginboard.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//로그인, 회원가입 API
@RestController
public class SignController {

    @Autowired
    private CustomUserDetailsService customUserDetailService;


    // 로그인을 통해 보안을 위한 JWT 토큰 발급
    @PostMapping(value = "/signin")
    @ResponseBody
    public SignDto signIn(HttpServletRequest request, @RequestBody AccountDto accountDto) {
        return customUserDetailService.signIn(accountDto);
    }

    // 회원 가입
    @PostMapping(value = "/signup")
    @ResponseBody
    public SignDto signUp(HttpServletRequest request, @RequestBody AccountDto signupAccount) throws Exception{
        return customUserDetailService.signUp(signupAccount);
    }
}