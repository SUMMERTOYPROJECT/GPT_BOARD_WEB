package com.jyp_board.board_app.service;

import com.jyp_board.board_app.domain.Article;
import com.jyp_board.board_app.domain.ArticleComment;
import com.jyp_board.board_app.domain.UserAccount;
import com.jyp_board.board_app.dto.ArticleCommentDto;
import com.jyp_board.board_app.dto.request.ArticleCommentRequest;
import com.jyp_board.board_app.dto.response.ArticleCommentResponse;
import com.jyp_board.board_app.repository.ArticleCommentRepository;
import com.jyp_board.board_app.repository.ArticleRepository;
import com.jyp_board.board_app.repository.UserAccountRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
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

    @Mock
    private UserAccountRepository userAccountRepository;

    @DisplayName("게시글 ID를 입력하면 해당 댓글 리스트를 반환한다.")
    @Test
    void given게시글ID_when댓글을반환_then댓글리스트를반환한다(){
        //given
        Long articleId = 1L;
        given(articleCommentRepository.findByArticle_Id(articleId)).willReturn(List.of());

        //when
        List<ArticleCommentResponse> comments = sut.searchArticleComments(articleId);
        //then
        assertThat(comments).isNotNull();
        then(articleCommentRepository).should().findByArticle_Id(articleId);
    }

    @DisplayName("게시글 댓글 ID를 입력하면 해당 댓글을 반환한다.")
    @Test
    void givenArticleCommentId_whenSearchArticleComment_thenReturnArticleComment(){
        // given

        Long articleCommentId = 1L;
        ArticleComment articleComment = createArticleComment();
        given(articleCommentRepository.findById(articleCommentId)).willReturn(Optional.of(articleComment));
        // when
        ArticleCommentDto articleCommentDto = sut.findById(articleCommentId);
        // then
        then(articleCommentRepository).should().findById(articleCommentId);
        assertThat(articleCommentDto).isNotNull();
    }

    @DisplayName("작성자 ID를 입력하면 작성한 댓글들을 모두 반환한다.")
    @Test
    void givenUserId_whenSearchArticleComment_thenReturnAllArticleComment(){
        // given
        UserAccount user = createUser();
        given(articleCommentRepository.findArticleCommentsByUserAccount(user)).willReturn(List.of());

        // when
        List<ArticleCommentDto> articleCommentDtoList = sut.searchArticleCommentsByUser(user);

        // then
        assertThat(articleCommentDtoList).isNotNull();
        then(articleCommentRepository).should().findArticleCommentsByUserAccount(user);
    }



    @Disabled("기능 구현 완료 테스트 확인 필요")
    @DisplayName("게시글 답변을 저장한다.")
    @Test
    void givenArticleIdWithComment_whenSavingComment_thenSaveArticleComment(){
        // given
        Article article = createArticle();
        UserAccount userAccount =createUser();

        ArticleCommentRequest request = ArticleCommentRequest.of(
                "content",
                userAccount.getUserId(),
                article.getId()
        );
//        System.out.println(userAccount.getUserId());
//        System.out.println(article.getId());

        given(articleCommentRepository.save(any(ArticleComment.class))).willReturn(null);
        // when
        sut.createArticleComment(request);
        // then
        then(articleCommentRepository).should().save(any(ArticleComment.class));
    }

    private UserAccount createUser(){
        return UserAccount.of(
                "userid",
                "password",
                "email",
                "nickname",
                "memo"
        );
    }
    private Article createArticle(){
        return Article.of(
                createUser(),
                "title",
                "content",
                "hashtag"
        );
    }

    private ArticleComment createArticleComment(){
        return ArticleComment.of(
                createArticle(),
                createUser(),
                "content"
        );
    }


}