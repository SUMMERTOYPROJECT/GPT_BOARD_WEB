package com.jyp_board.board_app.service;

import com.jyp_board.board_app.domain.Article;
import com.jyp_board.board_app.domain.type.SearchType;
import com.jyp_board.board_app.dto.ArticleDto;
import com.jyp_board.board_app.dto.request.ArticleRequest;
import com.jyp_board.board_app.dto.response.ArticleResponse;
import com.jyp_board.board_app.dto.ArticleUpdateDto;
import com.jyp_board.board_app.dto.ArticleWithCommentDto;
import com.jyp_board.board_app.repository.ArticleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    // READ 메소드 구현
    @Transactional(readOnly = true)
    public List<ArticleResponse> findAllResponse(){
        List<ArticleDto> dtos = articleRepository.findAll().stream().map(ArticleDto::from).toList();
        return dtos.stream().map(ArticleResponse::from).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ArticleResponse findResponse(Long id) {
        ArticleDto dto = ArticleDto.from(articleRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("게시글이 존재하지 않습니다.")
        ));
        return ArticleResponse.from(dto);
    }

    // CREATE 메소드 구현

    public void createArticle(ArticleRequest articleRequest){
        Article article = Article.of(
                articleRequest.userAccount(),
                articleRequest.title(),
                articleRequest.content(),
                articleRequest.hashtag()
        );
        articleRepository.save(article);
    }

    /* --- ----- ---- 리팩토링 중 --- -*/
    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType searchType, String keyword, Pageable pageable) {
        if (searchType == null || keyword == null || keyword.isBlank()){
            return articleRepository.findAll(pageable).map(ArticleDto::from);
        }
        switch (searchType){
            case TITLE -> articleRepository.findByTitleContaining(keyword, pageable).map(ArticleDto::from);
            case CONTENT-> articleRepository.findByContentContaining(keyword, pageable).map(ArticleDto::from);
            case ID -> articleRepository.findByUserAccount_UserIdContaining(keyword, pageable).map(ArticleDto::from);
            case NICKNAME -> articleRepository.findByUserAccount_NicknameContaining(keyword, pageable).map(ArticleDto::from);
            case HASHTAG -> articleRepository.findByHashtag("#" + keyword, pageable).map(ArticleDto::from);
        };
        return Page.empty();
    }

    @Transactional(readOnly = true)
    public ArticleWithCommentDto getArticleWithComment(Long articleId){
        return articleRepository.findById(articleId)
                .map(ArticleWithCommentDto::from)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다."));



    }

    public ArticleWithCommentDto searchArticle(Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(
                () -> new IllegalArgumentException("There is no Entity")
        );
        return ArticleWithCommentDto.from(article);
    }

    public void saveArticle(ArticleDto dto) {
        articleRepository.save(dto.toEntity());
    }

    public void updateArticle(ArticleUpdateDto dto) {
        try{
            Article article = articleRepository.getReferenceById(dto.id());
            if (dto.title() != null){article.setTitle(dto.title());}
            if (dto.content() != null){article.setContent(dto.content());}
            article.setHashtag(dto.hashtag());
//        articleRepository.save(article); #-- 알아서 감지하므로 명시하지 않아도 된다.
        }
        catch (EntityNotFoundException e){
            log.warn("게시글 정보를 찾지 못했습니다.");
        }

    }

    public void deleteArticle(long articleId) {
        try{
            Article article = articleRepository.getReferenceById(articleId);
            articleRepository.deleteById(articleId);
        }
        catch (EntityNotFoundException e){
            log.warn("게시글 정보를 찾지 못했습니다.");
        }
    }


}
