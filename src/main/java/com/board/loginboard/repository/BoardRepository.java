package com.board.loginboard.repository;

import com.board.loginboard.domain.Account;
import com.board.loginboard.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByTitleContaining(String searchWord);

    Board findByAccount(Account account);
}