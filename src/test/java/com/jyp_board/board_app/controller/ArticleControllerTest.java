package com.jyp_board.board_app.controller;

import com.jyp_board.board_app.config.SecurityConfig;

import com.jyp_board.board_app.dto.ArticleWithCommentDto;
import com.jyp_board.board_app.dto.UserAccountDto;
import com.jyp_board.board_app.service.ArticleService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDateTime;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("View 컨트롤러 - 게시판 테스트")
@AutoConfigureMockMvc
@Import(SecurityConfig.class)
@SpringBootTest
class ArticleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArticleService articleService;

    @DisplayName("[view][GET] 게시글 리스트 전체 조회 - 정상 호출")
    @Test
    public void givenNoting_whenRequestingView_thenReturnArticlesView() throws Exception {
        // Given
        given(articleService.searchArticles(eq(null), eq(null), any(Pageable.class))).willReturn(Page.empty());

        // When & Then
        mockMvc.perform(get("/articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/index"))
                .andExpect(model().attributeExists("articles"));

        then(articleService).should().searchArticles(eq(null), eq(null), any(Pageable.class));
    }

    @DisplayName("[view][GET] 게시글 상세 조회 - 정상 호출")
    @Test
    public void givenNoting_whenRequestingView_thenReturnArticleView() throws Exception {
        // Given
        Long articleId = 1L;
        given(articleService.searchArticle(articleId)).willReturn(createArticleWithCommentsDto());

        // When & Then
        mockMvc.perform(get("/articles/" + articleId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/detail"))
                .andExpect(model().attributeExists("article"))
                .andExpect(model().attributeExists("articleComments"));

        then(articleService).should().searchArticle(articleId);
    }



    @Disabled("구현 중")
    @DisplayName("[view][GET] 게시글 검색  - 정상 호출")
    @Test
    public void givenNoting_whenRequestingView_thenReturnArticleSearchView() throws Exception {
        // Given

        // When & Then
        mockMvc.perform(get("/articles/search"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/search"));
    }

    @Disabled("구현 중")
    @DisplayName("[view][GET] 게시글 해시태그  - 정상 호출")
    @Test
    public void givenNoting_whenRequestingView_thenReturnArticleHaseTagSearchView() throws Exception {
        // Given
        // When & Then
        mockMvc.perform(get("/articles/search-hashtag"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/search-hashtag"));

    }

    private ArticleWithCommentDto createArticleWithCommentsDto(){
        return ArticleWithCommentDto.of(
                1L,
                createUserAccountDto(),
                Set.of(),
                "title",
                "content",
                LocalDateTime.now(),
                "jyp",
                LocalDateTime.now(),
                "jyp",
                "#hash"
        );
    }
    private UserAccountDto createUserAccountDto(){
        return UserAccountDto.of(
                "MyuserId",
                "password",
                "email",
                "nickname",
                "memo",
                LocalDateTime.now(),
                "jyp",
                LocalDateTime.now(),
                "jyp"
        );

    }
}
