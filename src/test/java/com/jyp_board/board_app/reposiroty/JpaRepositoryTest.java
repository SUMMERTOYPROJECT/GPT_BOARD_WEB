package com.jyp_board.board_app.reposiroty;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import com.jyp_board.board_app.config.JpaConfig;
import com.jyp_board.board_app.domain.Article;
import com.jyp_board.board_app.repository.ArticleCommentRepository;
import com.jyp_board.board_app.repository.ArticleRepository;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
// @DataJpaTest #-- 인메모리 환경
@SpringBootTest
class JpaRepositoryTest {

    private final ArticleRepository articleRepository;    
    private final ArticleCommentRepository articleCommentRepository;

    public JpaRepositoryTest(
        @Autowired ArticleRepository articleRepository,
        @Autowired ArticleCommentRepository articleCommentRepository
    ){
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @DisplayName("Select 테스트")    
    @Test
    void given_when_then(){
        // given
        articleRepository.save(Article.of("테스트데이터입니다.", "테스트1", null));
        // when
        List<Article> articles = articleRepository.findAll();
        
        // // then
        assertThat(articles)
        .isNotNull()
        .hasSize(1);
    }


}
