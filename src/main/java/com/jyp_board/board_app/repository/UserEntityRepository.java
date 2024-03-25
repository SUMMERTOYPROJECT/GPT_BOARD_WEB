package com.jyp_board.board_app.repository;

import com.jyp_board.board_app.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByUsername(String Username);
    UserEntity findByUsername(String Username);
}
