package com.board.loginboard.controller;

import com.board.loginboard.dto.AccountDto;
import com.board.loginboard.dto.BoardDto;
import com.board.loginboard.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("")
    public List<AccountDto> fullList(){
        return accountService.getAccountList();
    }


}
