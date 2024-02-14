package com.jyp_board.board_app.service;

import com.jyp_board.board_app.domain.ArticleComment;
import com.jyp_board.board_app.domain.UserAccount;
import com.jyp_board.board_app.dto.ArticleCommentDto;
import com.jyp_board.board_app.dto.ArticleWithCommentDto;
import com.jyp_board.board_app.dto.request.ArticleCommentRequest;
import com.jyp_board.board_app.dto.request.ArticleCommentUpdateRequest;
import com.jyp_board.board_app.dto.request.ArticleRequest;
import com.jyp_board.board_app.dto.response.ArticleCommentResponse;
import com.jyp_board.board_app.repository.ArticleCommentRepository;
import com.jyp_board.board_app.repository.ArticleRepository;
import com.jyp_board.board_app.repository.UserAccountRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class ArticleCommentService {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;
    private final UserAccountRepository userAccountRepository;


    // userId를 통해 구현해야함
    @Transactional(readOnly = true)
    public ArticleCommentDto findById(Long articleCommentId){
        return ArticleCommentDto.from(articleCommentRepository.findById(articleCommentId)
                .orElseThrow(
                        () -> new IllegalArgumentException("일치하는 댓글이 존재하지 않습니다.")
                ));
    }

    @Transactional(readOnly = true)
    public List<ArticleCommentResponse> searchArticleComments(Long articleId) {
        List<ArticleComment> articleCommentList = articleCommentRepository.findByArticle_Id(articleId);
        return articleCommentList.stream().map(ArticleCommentDto::from).toList().stream().map(ArticleCommentResponse::from).collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public ArticleCommentResponse searchArticleComment(Long articleCommentId) {
        ArticleComment articleComment = articleCommentRepository.findById(articleCommentId).orElseThrow(
                () -> new EntityNotFoundException("댓글이 존재하지 않습니다.")
        );
        return ArticleCommentResponse.from(ArticleCommentDto.from(articleComment));
    }

    @Transactional(readOnly = true)
    public List<ArticleCommentDto> searchArticleCommentsByUser(UserAccount userAccount){
        return articleCommentRepository.findArticleCommentsByUserAccount(userAccount)
                .stream().map(ArticleCommentDto::from).collect(Collectors.toList());
    }

    public ResponseEntity<String> createArticleComment(ArticleCommentRequest articleCommentRequest){
        try{

            boolean isUser = userAccountRepository.existsByUserId(articleCommentRequest.userId());
            if(isUser){
                ArticleComment articleComment = ArticleComment.of(
                        articleRepository.findById(articleCommentRequest.articleId()).orElseThrow(() -> new EntityNotFoundException("게시글이 존재하지 않습니다.")),
                        userAccountRepository.findByUserId(articleCommentRequest.userId()),
                        articleCommentRequest.content()
                );
                articleCommentRepository.save(articleComment);
                return ResponseEntity.ok().body("답변이 정상적으로 등록되었습니다.");
            }
            return ResponseEntity.badRequest().body("답변 등록이 실파해였습니다.");
        }
        catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity<String> updateArticleComments(ArticleCommentUpdateRequest updateRequest) {
        try{
            ArticleComment articleComment = articleCommentRepository.getReferenceById(updateRequest.articleCommentId());
            articleComment.setContent(updateRequest.content());
            return ResponseEntity.ok().body("댓글이 정상적으로 수정되었습니다");
        }
        catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().body("댓글 수정에 실패했습니다.");
        }

    }
    public ResponseEntity<String> deleteArticleComments(Long articleCommentId) {
        try{
            articleCommentRepository.deleteById(articleCommentId);
            return ResponseEntity.ok().body("댓글이 정상적으로 삭제되었습니다.");
        }
        catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().body("댓글 삭제 중 오류가 발생했습니다.");
        }

    }
}
