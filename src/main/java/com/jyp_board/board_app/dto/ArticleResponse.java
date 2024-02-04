package com.jyp_board.board_app.dto;

import com.jyp_board.board_app.domain.Article;

import java.time.LocalDateTime;
import java.util.Set;

public record ArticleResponse(
        Long id,
        String title,
        String content,
        String hashtag,
        LocalDateTime createAt,
        String email,
        String nickname
) {
    public static ArticleResponse of(Long id, String title, String content, String hashtag,
                                     LocalDateTime createAt, String email, String nickname){
        return  new ArticleResponse(id, title, content, hashtag, createAt, email, nickname);
    }

    public static ArticleResponse from(ArticleDto dto){
        String nickname = dto.userAccountDto().nickname();
        if(nickname != null || nickname.isBlank()){
            nickname = dto.userAccountDto().userId();
        }
        return new ArticleResponse(
                dto.id(),
                dto.title(),
                dto.content(),
                dto.hashtag(),
                dto.createdAt(),
                dto.userAccountDto().email(),
                nickname
        );

    }




}
