package com.jyp_board.board_app.dto.request;

import com.jyp_board.board_app.domain.Article;
import com.jyp_board.board_app.domain.UserAccount;
import com.jyp_board.board_app.dto.UserAccountDto;

public record ArticleRequest(
        String title,
        String content,
        String hashtag,
        UserAccount userAccount

) {
    public static ArticleRequest of(String title, String content, String hashtag, UserAccount userAccount) {
        return new ArticleRequest(title, content, hashtag, userAccount);
    }


}
