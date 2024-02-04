package com.jyp_board.board_app.controller;

import com.jyp_board.board_app.domain.type.SearchType;
import com.jyp_board.board_app.dto.ArticleResponse;
import com.jyp_board.board_app.dto.ArticleWithCommentsResponse;
import com.jyp_board.board_app.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequiredArgsConstructor
@RequestMapping("/articles")
@Controller
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping
    public String articles(
            @RequestParam(required = false, name = "searchType") SearchType searchType,
            @RequestParam(required = false, name = "searchValue") String searchValue,
            @PageableDefault(size = 10, sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable,
            ModelMap model){
        model.addAttribute("articles", articleService.searchArticles(searchType, searchValue, pageable).map(ArticleResponse::from));
        return "articles/index";
    }

    @GetMapping("/{articleId}")
    public String article(@PathVariable(name = "articleId") Long articleId, ModelMap model){
        ArticleWithCommentsResponse article = ArticleWithCommentsResponse.from(articleService.searchArticle(articleId));

        model.addAttribute("article", article);
        model.addAttribute("articleComments", article.articleCommentsResponses());
        return "articles/detail";
    }


}
