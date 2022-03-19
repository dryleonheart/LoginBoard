package com.board.loginboard.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.board.loginboard.domain.Role;
import com.board.loginboard.dto.AccountDto;
import com.board.loginboard.dto.SignDto;
import com.board.loginboard.security.CustomUserDetailsService;
import com.board.loginboard.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignController {

    @Autowired
    private CustomUserDetailsService customUserDetailService;


    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    // signin, login
    @PostMapping(value = "/signin")
    @ResponseBody
    public SignDto signInUser(HttpServletRequest request, @RequestBody AccountDto accountDto) {
        AccountDto result = customUserDetailService.findByAccountId(accountDto.getAccountId());
        SignDto signDto = new SignDto();
        if (!(result.getAccountPw() == accountDto.getAccountPw())) {
            signDto.setResult("fail");
            signDto.setMessage("ID or Password is invalid.");
            return signDto;
        }
        String role = result.getRole().toString();
        signDto.setResult("success");
        signDto.setToken(jwtTokenProvider.createToken(result.getAccountId(), role));
        return signDto;
    }

    // signup,
    @PostMapping(value = "/signup")
    @ResponseBody
    public SignDto addUser(HttpServletRequest request, @RequestBody AccountDto signupAccount) {
        AccountDto user = signupAccount;
        user.setRole(Role.USER);
        user.setAccountPw(signupAccount.getAccountPw());
        SignDto signDto = new SignDto();
        int result = customUserDetailService.signInUser(user);
        if (result == 1) {
            signDto.setResult("success");
            signDto.setMessage("SignUp complete");
            return signDto;
        } else if (result == -1) {
            signDto.setResult("fail");
            signDto.setMessage("There is the same name, please change your name.");
            return signDto;
        } else {
            signDto.setResult("fail");
            signDto.setMessage("Ask system admin");
            return signDto;
        }
    }
}