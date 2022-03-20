package com.board.loginboard.security;

import com.board.loginboard.domain.Role;
import com.board.loginboard.dto.AccountDto;
import com.board.loginboard.dto.SignDto;
import com.board.loginboard.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {   //Spring Security와 연동하기 위해 account 테이블과 UserDetail 연동

    @Autowired
    AccountService accountService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    // accountId를 사용하여 acccount 조회 가능하도록 Override
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        return accountService.getAccountById(id);
    }

    public AccountDto findByAccountId(String name) {
        return accountService.getAccountById(name);
    }


    public SignDto signIn(AccountDto accountDto) {

        SignDto signDto = new SignDto();
        AccountDto result = this.findByAccountId(accountDto.getAccountId());

        //암호화 된 비밀번호 비교
        if (!(result.getAccountPw() == encoder.encode(accountDto.getAccountPw()))) {
            signDto.setResult("fail");
            signDto.setMessage("ID or Password is invalid.");
            return signDto;
        }

        //JWT 토큰 생성후 결과와 리턴
        signDto.setResult("success");
        signDto.setToken(jwtTokenProvider.createToken(result.getAccountId(), result.getRole().toString()));

        return signDto;
    }


    public SignDto signUp(AccountDto signupAccount) throws Exception{

        SignDto signDto = new SignDto();
        AccountDto user = signupAccount;

        user.setRole(Role.USER);                                                                    //역할 설정
        user.setAccountPw(encoder.encode(signupAccount.getAccountPw()));                            //비밀번호 암호화

        int result = this.signUpUser(user);

        if (result == 1) {
            signDto.setResult("success");
            signDto.setMessage("SignUp complete");
            return signDto;
        } else if (result == -1) {
            signDto.setResult("fail");
            signDto.setMessage("There is the same name, please change your name.");
            return signDto;
        } else {
            signDto.setResult("fail");
            signDto.setMessage("Ask system admin");
            return signDto;
        }
    }

    //회원가입 기능 - 지금은 ID중복만 확인했지만 조건 추가 가능
    public int signUpUser(AccountDto accountDto) throws Exception{
        if (accountService.getAccountById(accountDto.getAccountId()).getAccountId() == null) {
            accountService.save(accountDto);
            return 1;
        } else {
            return -1;
        }
    }

    //회원탈퇴 기능
    public int deleteUser(Long id) throws Exception{
        accountService.delete(id);
        return 1;
    }

}