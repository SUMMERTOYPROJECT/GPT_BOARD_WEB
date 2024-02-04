package com.jyp_board.board_app.service;

import com.jyp_board.board_app.domain.Article;
import com.jyp_board.board_app.dto.ArticleCommentDto;
import com.jyp_board.board_app.repository.ArticleCommentRepository;
import com.jyp_board.board_app.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
@DisplayName("비즈니스 로직 -댓글-")
class ArticleCommentServiceTest {
    @InjectMocks
    private ArticleCommentService sut; //system under test
    @Mock
    private ArticleCommentRepository articleCommentRepository;
    @Mock
    private ArticleRepository articleRepository;

    @DisplayName("게시글 ID를 입력하면 해당 댓글 리스트를 반환한다.")
    @Test
    void given게시글ID_when댓글을반환_then댓글리스트를반환한다(){
        //g
        Long articleId = 1L;

//        given(articleRepository.findById(articleId)).willReturn(Optional.of(
//                Article.of("title","content","hashtag")
//        ));

        //w
        List<ArticleCommentDto> comments = sut.searchArticleComments(articleId);
        //t
        assertThat(comments).isNotNull();
        then(articleRepository).should().findById(articleId);

    }


}