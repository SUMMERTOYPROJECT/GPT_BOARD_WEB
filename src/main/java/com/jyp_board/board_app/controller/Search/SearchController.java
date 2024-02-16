package com.jyp_board.board_app.controller.Search;

import com.jyp_board.board_app.domain.type.SearchType;
import com.jyp_board.board_app.dto.response.ArticleResponse;
import com.jyp_board.board_app.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/articles")
@RestController
public class SearchController {
    private final ArticleService articleService;

    @GetMapping("/search")
    public List<ArticleResponse> searchMyArticle(@RequestParam(name = "type") SearchType searchType,
                                                 @RequestParam(name = "keyword") String keyword)
    {
        return articleService.searchMyArticle(searchType, keyword);
    }


}
