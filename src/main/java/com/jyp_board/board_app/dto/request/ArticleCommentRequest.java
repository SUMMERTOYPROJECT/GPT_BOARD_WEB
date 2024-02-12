package com.jyp_board.board_app.dto.request;

import com.jyp_board.board_app.domain.Article;
import com.jyp_board.board_app.domain.ArticleComment;
import com.jyp_board.board_app.domain.UserAccount;

public record ArticleCommentRequest(
        String content,
        String userId,
        Long articleId

) {
    public static ArticleCommentRequest of(String content, String userId, Long articleId) {
        return new ArticleCommentRequest(content, userId, articleId);
    }
}
