package com.jyp_board.board_app.repository;

import com.jyp_board.board_app.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    
}
