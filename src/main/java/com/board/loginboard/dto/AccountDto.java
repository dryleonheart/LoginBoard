package com.board.loginboard.dto;


import com.board.loginboard.domain.Account;
import com.board.loginboard.domain.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AccountDto implements UserDetails {     // 계정Dto 임과 동시에 Spring Security 이용을 위한 UserDetails 상속
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

    // 인증시에 필요한 Password, Username 정보 대응되는 값으로 return 하도록 Override
    @Override
    public String getPassword() {
        return accountPw;
    }

    @Override
    public String getUsername() {
        return accountId;
    }

    //계정 유효성 처리 -> 일단은 True로 통일하고 필요에 따라 Table에 정보 추가및 기능 정의 가능
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
