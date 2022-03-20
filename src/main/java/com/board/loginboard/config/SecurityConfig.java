package com.board.loginboard.config;

import com.board.loginboard.security.JwtAuthenticationFilter;
import com.board.loginboard.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// Spring Security 설정
@RequiredArgsConstructor
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    //비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    //설정 상세
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()                                                                                          // rest api 이므로 기본설정 사용X
                .csrf().disable()                                                                                               // rest api이므로 csrf 보안이 필요없으므로 disable처리.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()                               // jwt token으로 인증 예정
                .authorizeRequests()                                                                                            // 다음 리퀘스트에 대한 사용권한 체크
                .antMatchers("/signin", "/signup").permitAll()                                                       // 로그인 및 가입주소는 접근 자유
                .anyRequest().hasRole("USER").and()                                                                             // 그외 나머지 요청은 모두 인증된 회원만 접근 가능
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);    // jwt token 필터를 id/password 인증 필터 전에 넣는다
    }
}
