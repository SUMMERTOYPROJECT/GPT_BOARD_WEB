package com.jyp_board.board_app.service;

import com.jyp_board.board_app.domain.ArticleComment;
import com.jyp_board.board_app.dto.ArticleCommentDto;
import com.jyp_board.board_app.repository.ArticleCommentRepository;
import com.jyp_board.board_app.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ArticleCommentService {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    @Transactional(readOnly = true)
    public List<ArticleCommentDto> searchArticleComments(Long articleId) {

        return List.of();
    }
    public void saveArticleComment(ArticleCommentDto dto) {

    }
    public void updateArticleComments(ArticleCommentDto dto) {

    }
    public void deleteArticleComments(ArticleCommentDto dto) {

    }
}
