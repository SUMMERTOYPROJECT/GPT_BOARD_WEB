package com.jyp_board.board_app.service;

import com.jyp_board.board_app.domain.Article;
import com.jyp_board.board_app.domain.type.SearchType;
import com.jyp_board.board_app.dto.ArticleDto;
import com.jyp_board.board_app.dto.ArticleUpdateDto;
import com.jyp_board.board_app.dto.ArticleWithCommentDto;
import com.jyp_board.board_app.dto.UserAccountDto;
import com.jyp_board.board_app.dto.request.ArticleRequest;
import com.jyp_board.board_app.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("비즈니스 로직 -게시글-")
class ArticleServiceTest {
    @InjectMocks
    private ArticleService sut; //system under test
    @Mock
    private ArticleRepository articleRepository;

    @DisplayName("검색어 없이 검색하면 게시글 페이지를 반환한다.")
    @Test
    void givenNotting_whenSearchingArticles_thenReturnArticlesPage() {
        // given
        Pageable pageable = Pageable.ofSize(20);
        given(articleRepository.findAll(pageable)).willReturn(Page.empty());
        // when
        Page<ArticleDto> articles = sut.searchArticles(null, "keyword", pageable);
        // then
        assertThat(articles).isEmpty();
        then(articleRepository).should().findAll(pageable);
    }


    @DisplayName("검색어와 함께 게시글을 검색하면 게시글 페이지를 반환한다.")
    @Test
    void given검색어_whenSearchingArticles_thenReturnArticlesPage() {
        // given
        SearchType searchType = SearchType.TITLE;
        String keyword = "title";
        Pageable pageable = Pageable.ofSize(20);
        given(articleRepository.findByTitleContaining(keyword, pageable)).willReturn(Page.empty());

        // when
        Page<ArticleDto> articles = sut.searchArticles(searchType, keyword, pageable);
        // then
        assertThat(articles).isEmpty();
        then(articleRepository).should().findByTitleContaining(keyword, pageable);
    }


    @DisplayName("게시글을 요청하면 게시글을 반환한다.")
    @Test
    void givenArticleId_whenSearchingArticle_thenReturnArticle() {
        UserAccountDto userAccountDto = UserAccountDto.of("userId", "password", "email", "nickName", "memo");
        ArticleDto dto = ArticleDto.of(1L, userAccountDto, LocalDateTime.now(), LocalDateTime.now(), "jyp", "jyp", "title", "content", "hashtag");
        Long articleId = dto.id();
        // given
        given(articleRepository.findById(articleId)).willReturn(Optional.of(dto.toEntity()));
        // when
        ArticleWithCommentDto articleWithCommentDto = sut.searchArticle(articleId);
        // then
        then(articleRepository).should().findById(articleId);
    }

    @DisplayName("게시글 정보를 입력하면 게시글을 생성한다.")
    @Test
    void givenArticleInfo_whenSavingArticle_thenSaveArticle() {
        // given
        UserAccountDto userAccountDto = UserAccountDto.of(
                "userId", "password", "email", "nickName", "memo"
        );

        ArticleRequest dto = ArticleRequest.of("title", "content", "has","userId");

        given(articleRepository.save(any(Article.class))).willReturn(null);

        // when
        sut.createArticle(dto);
        // then
        then(articleRepository).should().save(any(Article.class));
    }

    @DisplayName("게시글 ID와 수정데이터를 입력하면 게시글을 수정.")
    @Test
    void givenID와수정데이터_when게시글을수정한다_then게시글을수정한다() {
        // given
        UserAccountDto userAccountDto = UserAccountDto.of("userId", "password", "email", "nickName", "memo");
        ArticleDto dto = ArticleDto.of(1L, userAccountDto, LocalDateTime.now(), LocalDateTime.now(), "jyp", "jyp", "title", "content", "hashtag");

        given(articleRepository.getReferenceById(dto.id())).willReturn(dto.toEntity());

        // when
        sut.updateArticle(ArticleUpdateDto.of(1L, "title", "content", "hashtag"));
        // then
        assertThat(dto.toEntity())
                .hasFieldOrPropertyWithValue("title", dto.title())
                .hasFieldOrPropertyWithValue("content", dto.content())
                .hasFieldOrPropertyWithValue("hashtag", dto.hashtag());
        then(articleRepository).should().getReferenceById(dto.id());
    }

    @DisplayName("게시글 ID를 이용하여 게시글을 삭제한다.")
    @Test
    void givenArticleID_when게시글삭제_then게시글을삭제한다() {
        // given
        UserAccountDto userAccountDto = UserAccountDto.of("userId", "password", "email", "nickName", "memo");
        ArticleDto dto = ArticleDto.of(1L, userAccountDto, LocalDateTime.now(), LocalDateTime.now(), "jyp", "jyp", "title", "content", "hashtag");
        Long articleId = dto.id();

        willDoNothing().given(articleRepository).deleteById(articleId);

        // when
        sut.deleteArticle(articleId);
        // then
        then(articleRepository).should().deleteById(articleId);
    }


}