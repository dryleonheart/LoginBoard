package com.board.loginboard.security;

import com.board.loginboard.dto.AccountDto;
import com.board.loginboard.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        return accountService.getAccountById(id);
    }

    public AccountDto findByAccountId(String name) {
        return accountService.getAccountById(name);
    }

    public int signInUser(AccountDto accountDto) {
        if (accountService.getAccountById(accountDto.getAccountId()).getAccountId() == null) {
            accountService.save(accountDto);
            return 1;
        } else {
            return -1;
        }
    }

    public int deleteUser(Long id) {
        accountService.delete(id);
        return 1;
    }

}