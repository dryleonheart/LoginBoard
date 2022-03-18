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
@Table(name = "account")
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
    public Account(Long id, String accountId, String accountPw, Role role){
        Assert.hasText(accountId, "accountId is Empty");
        Assert.hasText(accountPw, "accountPw is Empty");

        this.id = id;
        this.accountId = accountId;
        this.accountPw = accountPw;
        this.role = role;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }
}