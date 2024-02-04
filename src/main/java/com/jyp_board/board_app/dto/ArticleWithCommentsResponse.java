package com.jyp_board.board_app.dto;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public record ArticleWithCommentsResponse(
        Long id,
        String title,
        String content,
        String hashtag,

        LocalDateTime createAt,
        String email,
        String nickname,
        String userId,

        Set<ArticleCommentResponse> articleCommentsResponses

) {

    public static ArticleWithCommentsResponse of(Long id, String title, String content, String hashtag,
                                                 LocalDateTime createAt, String email, String nickname, String userId,
                                                 Set<ArticleCommentResponse> articleCommentResponses
    ){
        return new ArticleWithCommentsResponse(id, title, content, hashtag, createAt, email, nickname, userId, articleCommentResponses);
    }
    public static ArticleWithCommentsResponse from(ArticleWithCommentDto dto){
        String nickname = dto.userAccountDto().nickname();
        if (nickname == null || nickname.isBlank()){
            nickname = dto.userAccountDto().userId();
        }

        return ArticleWithCommentsResponse.of(
                dto.id(),
                dto.title(),
                dto.content(),
                dto.hashtag(),
                dto.createdAt(),
                dto.userAccountDto().email(),
                nickname,
                dto.userAccountDto().userId(),
                dto.articleCommentDtos().stream()
                        .map(ArticleCommentResponse::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new))

        );
    }


}
