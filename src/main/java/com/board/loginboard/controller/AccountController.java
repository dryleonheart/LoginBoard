package com.board.loginboard.controller;

import com.board.loginboard.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/account")
public class AccountController {
    private AccountService accountService;


}
