package com.jyp_board.board_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jyp_board.board_app.domain.ArticleComment;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long>{
    
}
