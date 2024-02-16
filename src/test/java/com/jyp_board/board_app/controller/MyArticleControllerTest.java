package com.jyp_board.board_app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jyp_board.board_app.config.SecurityConfig;

import com.jyp_board.board_app.domain.Article;
import com.jyp_board.board_app.dto.ArticleUpdateDto;
import com.jyp_board.board_app.dto.ArticleWithCommentDto;
import com.jyp_board.board_app.dto.UserAccountDto;
import com.jyp_board.board_app.dto.request.ArticleRequest;
import com.jyp_board.board_app.dto.response.ArticleResponse;
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
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("Article 컨트롤러 - 게시판 테스트")
@AutoConfigureMockMvc
@Import(SecurityConfig.class)
@SpringBootTest
class MyArticleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArticleService articleService;

    @DisplayName("[GET] 게시글 리스트 전체 조회 - 정상 호출")
    @Test
    public void givenNoting_whenRequestingView_thenReturnArticlesView() throws Exception {
        // Given
        given(articleService.searchMyArticle(eq(null), eq(null))).willReturn(List.of());

        // When & Then
        mockMvc.perform(get("/articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML));

//        then(articleService).should().searchMyArticle(eq(null), eq(null), any(List.of()));
    }


    @DisplayName("[GET] 게시글 상세 조회 - 정상 호출")
    @Test
    public void givenNoting_whenRequestingView_thenReturnArticleView() throws Exception {
        // Given
        Long articleId = 1L;
        given(articleService.searchArticle(articleId)).willReturn(createArticleWithCommentsDto());


        // When & Then
        mockMvc.perform(get("/articles/" + articleId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML));

        then(articleService).should().searchArticle(articleId);
    }
    @DisplayName("[POST] 게시글 등록 - 정상 호출")
    @Test
    public void givenArticleReq_whenSaveArticle_thenReturnArticle() throws  Exception{
        // given
        ArticleRequest request = ArticleRequest.of(
                "title","content", "hashtag","userId"
        );
        given(articleService.createArticle(request)).willReturn(ResponseEntity.ok().build());

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonContent = objectMapper.writeValueAsString(request);


        // when & then
        mockMvc.perform(post("/api/articles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                        .andExpect(status().isOk());
        // when & then
        then(articleService).should().createArticle(request);
    }


    @DisplayName("[DELETE] 게시글 삭제 - 정상 호출")
    @Test
    public void givenArticleId_whenDeleteArticle_thenReturnDeleteArticle() throws Exception {
        //given
        Long articleId = 1L;
        given(articleService.deleteArticle(articleId)).willReturn(ResponseEntity.ok().build());

        // when & then
        mockMvc.perform(delete("/api/articles/1"))
                .andExpect(status().isOk());

        then(articleService).should().deleteArticle(articleId);
    }

    @DisplayName("[PUT] 게시글 수정 - 정상 호출")
    @Test
    public void givenArticleId_whenUpdateArticle_thenReturnUpdateArticle() throws Exception {
        //given
        String title = "hello title";
        String content = "hello content";
        String hashtag = "hello hashtag";

        Long articleId = 1L;
        ArticleUpdateDto updateDto = ArticleUpdateDto.of(
                articleId,
                title,
                content,
                hashtag
        );

        // ObjectMapper를 사용하여 DTO 객체를 JSON 문자열로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonContent = objectMapper.writeValueAsString(updateDto);
        given(articleService.updateArticle(updateDto)).willReturn(ResponseEntity.ok().build());

        // when & then
        mockMvc.perform(put("/api/articles/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk());

        then(articleService).should().updateArticle(updateDto);
    }
    @Disabled("구현 중")
    @DisplayName("[GET] 게시글 검색  - 정상 호출")
    @Test
    public void givenNoting_whenRequestingView_thenReturnArticleSearchView() throws Exception {
        // Given

        // When & Then
        mockMvc.perform(get("/articles/search"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML));
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
