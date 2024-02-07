package com.jyp_board.board_app.controller.Article;

import com.jyp_board.board_app.dto.request.ArticleRequest;
import com.jyp_board.board_app.dto.response.ArticleResponse;
import com.jyp_board.board_app.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MyArticleController {
    private final ArticleService articleService;

    @GetMapping("/articles")
    public List<ArticleResponse> getArticles(){
        return articleService.findAllResponse();
    }

    @GetMapping("/articles/{id}")
    public ArticleResponse getArticle(@PathVariable(name = "id") Long id){
        return articleService.findResponse(id);
    }

    @PostMapping("/articles")
    public ResponseEntity<String> createArticle(@RequestBody ArticleRequest articleRequest){
        // 게시글을 생성할 때 필요한 데이터
        // title, content, hashtag(null 가능), userAccount....
        return articleService.createArticle(articleRequest);
    }
}
