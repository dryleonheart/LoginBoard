package com.board.loginboard.dto;


import com.board.loginboard.domain.Account;
import com.board.loginboard.domain.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AccountDto implements UserDetails {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
        auth.add(new SimpleGrantedAuthority(role.toString()));
        return auth;
    }

    @Override
    public String getPassword() {
        return accountPw;
    }

    @Override
    public String getUsername() {
        return accountId;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isEnabled() {
        return true;
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
