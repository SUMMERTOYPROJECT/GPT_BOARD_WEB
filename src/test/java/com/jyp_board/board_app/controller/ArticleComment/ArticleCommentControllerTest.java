package com.jyp_board.board_app.controller.ArticleComment;

import com.jyp_board.board_app.config.SecurityConfig;
import com.jyp_board.board_app.service.ArticleCommentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DisplayName("ArticleComment 컨트롤러 - 게시판 테스트")
@AutoConfigureMockMvc
@Import(SecurityConfig.class)
@SpringBootTest
class ArticleCommentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArticleCommentService articleCommentService;

    @DisplayName("[GET] 게시글 댓글 전체 조회 - 정상 호출 - ")
    @Test
    void givenArticleId_whenSearchArticleComments_thenReturnArticleCommentList() throws Exception {
        //given
        Long articleId = 1L;
        given(articleCommentService.searchArticleComments(articleId)).willReturn(List.of());

        //when N then
        mockMvc.perform(get("/api/articleComment")
                .param("id", String.valueOf(articleId))
        ).andExpect(status().isOk());
        BDDMockito.then(articleCommentService).should().searchArticleComments(articleId);

    }
}