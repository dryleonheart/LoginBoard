package com.board.loginboard.service;

import com.board.loginboard.domain.Account;
import com.board.loginboard.domain.Board;
import com.board.loginboard.dto.AccountDto;
import com.board.loginboard.dto.BoardDto;
import com.board.loginboard.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountService {
    private AccountRepository accountRepository;

    private AccountDto convertEntityToDto(Account account){
        return AccountDto
                .builder()
                .accountId(account.getAccountId())
                .accountPw(account.getAccountPw())
                .role(account.getRole())
                .createdDate(account.getCreatedDate())
                .modifiedDate(account.getModifiedDate())
                .build();
    }

    @Transactional
    public Boolean login(AccountDto accountDto){
        Optional<Account> accountWrap = accountRepository.findByAccountId(accountDto.getAccountId());
        if(accountWrap.isPresent()){
            Account account = accountWrap.get();

            if(account.getAccountPw().equals(accountDto.getAccountPw())){
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    @Transactional
    public Boolean checkAccountId(AccountDto accountDto){
        Optional<Account> accountWrap = accountRepository.findByAccountId(accountDto.getAccountId());

        if(accountWrap.isEmpty()){
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    @Transactional
    public Long save(AccountDto accountDto){
        return accountRepository.save(accountDto.toEntity()).getId();
    }

    @Transactional
    public void delete(Long id){
        accountRepository.deleteById(id);
    }
}
