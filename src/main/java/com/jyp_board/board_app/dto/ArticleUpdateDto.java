package com.jyp_board.board_app.dto;

public record ArticleUpdateDto(
        Long id,
        String title,
        String content,
        String hashtag
){
    public static ArticleUpdateDto of(Long id, String title, String content, String hashtag) {
        return new ArticleUpdateDto(id, title, content, hashtag);
    }
}
