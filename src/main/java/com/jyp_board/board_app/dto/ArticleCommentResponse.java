package com.jyp_board.board_app.dto;

import java.time.LocalDateTime;

public record ArticleCommentResponse(
        Long id,
        String content,
        LocalDateTime createAt,
        String email,
        String nickname,
        String userId
) {
    public static ArticleCommentResponse of(Long id, String content, LocalDateTime createAt, String email,
                                            String nickname, String userId){
        return new ArticleCommentResponse(id, content, createAt, email, nickname, userId);
    }
    public static ArticleCommentResponse from(ArticleCommentDto dto){
        String nickname = dto.userAccountDto().nickname();
        if (nickname == null || nickname.isBlank()){
            nickname = dto.userAccountDto().userId();
        }

        return ArticleCommentResponse.of(
                dto.id(),
                dto.content(),
                dto.createdAt(),
                dto.userAccountDto().email(),
                nickname,
                dto.userAccountDto().userId()
        );
    }


}
