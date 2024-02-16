package com.jyp_board.board_app.controller.Search;

import com.jyp_board.board_app.config.SecurityConfig;
import com.jyp_board.board_app.service.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;


@DisplayName("Search 컨트롤러 - 검색 기능 테스트")
@AutoConfigureMockMvc
@Import(SecurityConfig.class)
@SpringBootTest
class SearchControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArticleService articleService;

    @DisplayName("[GET] TITLE 일부를 입력하면 일치하는 게시글을 반환한다.")
    @Test
    void givenTITLE_whenSearchArticle_thenReturnArticle(){
        // given
//        given(articleService.)

        // when

        // then
    }


}