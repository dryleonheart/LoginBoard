package com.board.loginboard.dto;


import com.board.loginboard.domain.Account;
import com.board.loginboard.domain.Role;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AccountDto {
    private Long id;
    private String accountId;
    private String accountPw;
    private Role role;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Account toEntity(){
        return Account
                .builder()
                .accountId(accountId)
                .accountPw(accountPw)
                .role(role)
                .build();
    }

    @Builder
    public AccountDto(String accountId,  String accountPw, Role role, LocalDateTime createdDate, LocalDateTime modifiedDate){

        this.accountId = accountId;
        this.accountPw = accountPw;
        this.role = role;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
