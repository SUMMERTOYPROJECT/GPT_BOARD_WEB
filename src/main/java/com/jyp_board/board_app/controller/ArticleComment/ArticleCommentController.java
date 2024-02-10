package com.jyp_board.board_app.controller.ArticleComment;

import com.jyp_board.board_app.dto.ArticleCommentDto;
import com.jyp_board.board_app.service.ArticleCommentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class ArticleCommentController {
    private final ArticleCommentService articleCommentService;
    private final Logger logger = LoggerFactory.getLogger(ArticleCommentController.class);

    @GetMapping("/articleComment")
    public List<ArticleCommentDto> getArticleComment(@RequestParam(name = "id") Long articleId){
        logger.debug("this article id is " + articleId);
        return articleCommentService.searchArticleComments(articleId);
    }
}
