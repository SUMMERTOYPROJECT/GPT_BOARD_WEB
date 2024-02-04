package com.jyp_board.board_app.dto;
import com.jyp_board.board_app.domain.Article;
import com.jyp_board.board_app.domain.UserAccount;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public record ArticleDto(
        Long id,
        UserAccountDto userAccountDto,
        LocalDateTime modifiedAt,
        LocalDateTime createdAt,
        String createdBy,
        String modifiedBy,
        String title,
        String content,
        String hashtag
){
    public static ArticleDto of(Long id, UserAccountDto userAccountDto, LocalDateTime modifiedAt, LocalDateTime createdAt, String createdBy, String modifiedBy, String title, String content, String hashtag) {
        return  new ArticleDto(id, userAccountDto, modifiedAt, createdAt, createdBy, modifiedBy, title, content, hashtag);
    }

    public static ArticleDto from(Article entity){
        return new ArticleDto(
                entity.getId(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getModifiedAt(),
                entity.getCreateAt(),
                entity.getCreateBy(),
                entity.getModifiedBy(),
                entity.getTitle(),
                entity.getContent(),
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
