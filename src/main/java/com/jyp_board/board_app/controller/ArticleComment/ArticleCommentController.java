package com.jyp_board.board_app.controller.ArticleComment;

import com.jyp_board.board_app.dto.ArticleCommentDto;
import com.jyp_board.board_app.dto.request.ArticleCommentRequest;
import com.jyp_board.board_app.dto.request.ArticleCommentUpdateRequest;
import com.jyp_board.board_app.dto.response.ArticleCommentResponse;
import com.jyp_board.board_app.service.ArticleCommentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/articles")
@RestController
public class ArticleCommentController {
    private final ArticleCommentService articleCommentService;
    private final Logger logger = LoggerFactory.getLogger(ArticleCommentController.class);

    // 게시글에 연관된 모든 댓글들 조회
    @GetMapping("/{articleId}/articleComments")
    public List<ArticleCommentResponse> getArticleComments(@PathVariable(name = "articleId") Long articleId){
        logger.debug("this article id is " + articleId);
        return articleCommentService.searchArticleComments(articleId);
    }

    // 게시글에 연관된 단일 댓글 조회
    @GetMapping("/{articleId}/articleComments/{articleCommentId}")
    public ArticleCommentResponse getArticleComment(@PathVariable(name = "articleId") Long articleId, @PathVariable(name = "articleCommentId") Long articleCommentId){
        logger.debug("This ArticleComment id is " + articleCommentId);
        return articleCommentService.searchArticleComment(articleCommentId);
    }

    // 댓글 등록
    // /api/articles/{article-id}/articleComments
    @PostMapping("/{articleId}/articleComments")
    public ResponseEntity<String> postArticleComment(@PathVariable(name = "articleId")Long articleId,
                                                     @RequestBody ArticleCommentRequest articleCommentRequest){
        logger.debug("this article id is " + articleId);
        return articleCommentService.createArticleComment(articleCommentRequest);
    }

    // 댓글 수정
    @PutMapping("/{articleId}/articleComments/{articleCommentId}")
    public ResponseEntity<String> putArticleComment(@PathVariable(name="articleId") Long articleId,
                                                    @PathVariable(name="articleCommentId")Long articleCommentId,
                                                    @RequestBody ArticleCommentUpdateRequest updateRequest){
        logger.debug("This ArticleComment id is " + articleCommentId);
        return articleCommentService.updateArticleComments(updateRequest);
    }

    // 댓글 삭제
    @DeleteMapping("/{articleId}/articleComments/{articleCommentId}")
    public ResponseEntity<String> deleteArticleComment(@PathVariable(name = "articleId")Long articleId,
                                                       @PathVariable(name = "articleCommentId")Long articleCommentId)
    {
        return articleCommentService.deleteArticleComments(articleCommentId);
    }
}
