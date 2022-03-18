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
                .id(id)
                .accountId(accountId)
                .accountPw(accountPw)
                .build();
    }

    @Builder
    public AccountDto(Long id, String accountId,  String accountPw, LocalDateTime createdDate, LocalDateTime modifiedDate){
        this.id= id;
        this.accountId = accountId;
        this.accountPw = accountPw;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
