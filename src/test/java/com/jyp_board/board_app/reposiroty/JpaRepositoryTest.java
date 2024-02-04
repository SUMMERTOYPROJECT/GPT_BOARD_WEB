package com.jyp_board.board_app.reposiroty;

import java.util.List;

import com.jyp_board.board_app.domain.UserAccount;
import com.jyp_board.board_app.repository.UserAccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
//import com.jyp_board.board_app.config.JpaConfig;
import com.jyp_board.board_app.domain.Article;
import com.jyp_board.board_app.repository.ArticleCommentRepository;
import com.jyp_board.board_app.repository.ArticleRepository;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("JPA 연결 테스트")
//@Import(JpaConfig.class)
// @DataJpaTest #-- 인메모리 환경
@SpringBootTest
class JpaRepositoryTest {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;
    private final UserAccountRepository userAccountRepository;

    public JpaRepositoryTest(
        @Autowired ArticleRepository articleRepository,
        @Autowired ArticleCommentRepository articleCommentRepository,
        @Autowired UserAccountRepository userAccountRepository
    ){
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
        this.userAccountRepository = userAccountRepository;
    }

    @DisplayName("Select 테스트")    
    @Test
    void given_when_then(){
        // given
        long preCnt = articleRepository.count();
        UserAccount userAccount = userAccountRepository.save(
                UserAccount.of("jyp", "pw", null, null, null)
        );
        Article article = Article.of(userAccount, "new Article", "new Content","new Hashtag");
        // when
        articleRepository.save(article);

        // // then
        assertThat(articleRepository.count()).isEqualTo(preCnt+1);
    }


}
