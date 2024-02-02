package com.jyp_board.board_app.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("View 컨트롤러 - 게시판 테스트")
@WebMvcTest(ArticleController.class)
class ArticleControllerTest {
    private final MockMvc mockMvc;
    public ArticleControllerTest(@Autowired  MockMvc mockMvc){
        this.mockMvc =mockMvc;
    }

    @Disabled("구현 중")
    @DisplayName("[view][GET] 게시글 리스트 전체 조회 - 정상 호출")
    @Test
    public void givenNoting_whenRequestingView_thenReturnArticlesView() throws Exception {
        // Given

        // When & Then
        mockMvc.perform(get("/articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/index"))
                .andExpect(model().attributeExists("articles"));

    }
    @Disabled("구현 중")
    @DisplayName("[view][GET] 게시글 상세 조회 - 정상 호출")
    @Test
    public void givenNoting_whenRequestingView_thenReturnArticleView() throws Exception {
        // Given

        // When & Then
        mockMvc.perform(get("/articles/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/detail"))
                .andExpect(model().attributeExists("article"))
                .andExpect(model().attributeExists("articleComments"));

    }
    @Disabled("구현 중")
    @DisplayName("[view][GET] 게시글 검색  - 정상 호출")
    @Test
    public void givenNoting_whenRequestingView_thenReturnArticleSerachView() throws Exception {
        // Given

        // When & Then
        mockMvc.perform(get("/articles/search"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/search"));

    }
    @Disabled("구현 중")
    @DisplayName("[view][GET] 게시글 해시태그  - 정상 호출")
    @Test
    public void givenNoting_whenRequestingView_thenReturnArticleHaseTagSerachView() throws Exception {
        // Given
        // When & Then
        mockMvc.perform(get("/articles/search-hashtag"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/search-hashtag"));

    }
}
