package com.board.loginboard.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "account")            //ID와 PW 역할만 있는 기본적인 계정구조
public class Account extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String accountId;

    @Column(nullable = false)
    private String accountPw;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public Account(String accountId, String accountPw, Role role){
        Assert.hasText(accountId, "accountId is empty");
        Assert.hasText(accountPw, "(accountPw is empty");

        this.accountId = accountId;
        this.accountPw = accountPw;
        this.role = role;
    }
    public String getRoleKey(){
        return this.role.getKey();
    }
}