package com.jyp_board.board_app.dto.request;

public record ArticleCommentUpdateRequest(
        Long articleCommentId,
        String content,
        String userId,
        Long articleId


) {
    public static ArticleCommentUpdateRequest of(Long articleCommentId, String content, String userId, Long articleId) {
        return new ArticleCommentUpdateRequest(articleCommentId, content, userId, articleId);
    }
}
