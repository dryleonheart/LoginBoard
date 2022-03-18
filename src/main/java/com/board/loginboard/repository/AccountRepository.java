package com.board.loginboard.repository;

import com.board.loginboard.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUserId(String userId);
}