package com.jyp_board.board_app.dto;
import com.jyp_board.board_app.domain.Article;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public record ArticleWithCommentDto(
        Long id,
        UserAccountDto userAccountDto,
        Set<ArticleCommentDto> articleCommentDtos,
        String title,
        String content,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy,
        String hashtag
){

    public static ArticleWithCommentDto of(Long id, UserAccountDto userAccountDto, Set<ArticleCommentDto> articleCommentDtos, String title, String content,  LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy, String hasgtag) {
        return new ArticleWithCommentDto(id, userAccountDto, articleCommentDtos, title, content, createdAt, createdBy, modifiedAt, modifiedBy, hasgtag);
    }

    public static ArticleWithCommentDto from(Article entity){
        return new ArticleWithCommentDto(
                entity.getId(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getArticleComments().stream()
                        .map(ArticleCommentDto::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new))
                ,
                entity.getTitle(),
                entity.getContent(),
                entity.getCreateAt(),
                entity.getCreateBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy(),
                entity.getHashtag()
        );
    }

    public  Article toEntity(){
        return Article.of(
                userAccountDto.toEntity(),
                title,
                content,
                hashtag
        );
    }
}
