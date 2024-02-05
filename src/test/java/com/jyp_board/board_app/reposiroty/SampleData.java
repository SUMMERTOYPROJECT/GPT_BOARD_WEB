package com.jyp_board.board_app.reposiroty;

import com.jyp_board.board_app.domain.UserAccount;
import com.jyp_board.board_app.dto.ArticleDto;
import com.jyp_board.board_app.dto.UserAccountDto;
import com.jyp_board.board_app.repository.UserAccountRepository;
import com.jyp_board.board_app.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class SampleData {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserAccountRepository accountRepository;

    @Test
    void 테스트_데이터_생성(){

        for(int i=0; i<100; i++){
            UserAccountDto userAccountDto = new UserAccountDto(
                    "jyp", "password","email",
                    "nickname", "memo",
                    LocalDateTime.now(), "me",
                    LocalDateTime.now(), "me"
            );
            accountRepository.save(userAccountDto.toEntity());
            ArticleDto articleDto = new ArticleDto(
                    (long) (i+1),
                    userAccountDto,
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    "me","me",
                    (i+1) + "번째 title", (i+1) + "번째 content", (i+1) + "번째 hash"
            );

            articleService.saveArticle(articleDto);
        }


    }
}
