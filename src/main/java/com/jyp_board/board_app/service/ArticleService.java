package com.jyp_board.board_app.service;

import com.jyp_board.board_app.domain.Article;
import com.jyp_board.board_app.domain.UserAccount;
import com.jyp_board.board_app.domain.type.SearchType;
import com.jyp_board.board_app.dto.ArticleDto;
import com.jyp_board.board_app.dto.request.ArticleRequest;
import com.jyp_board.board_app.dto.response.ArticleResponse;
import com.jyp_board.board_app.dto.ArticleUpdateDto;
import com.jyp_board.board_app.dto.ArticleWithCommentDto;
import com.jyp_board.board_app.repository.ArticleRepository;
import com.jyp_board.board_app.repository.UserAccountRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
    private final UserAccountRepository userAccountRepository;

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

    public ResponseEntity<String> createArticle(ArticleRequest articleRequest){
        boolean isUser = userAccountRepository.existsByUserId(articleRequest.userId());
        if(isUser) {
            UserAccount user = userAccountRepository.findByUserId(articleRequest.userId());
            Article article = Article.of(
                    user,
                    articleRequest.title(),
                    articleRequest.content(),
                    articleRequest.hashtag()
            );
            articleRepository.save(article);
            return ResponseEntity.ok("게시글 작성이 완료되었습니다.");
        }
        return ResponseEntity.badRequest().body("존재하지 않은 회원입니다.");

    }

    // UPDATE 구현

    public ResponseEntity<String> updateArticle(ArticleUpdateDto dto) {
        try{
            Article article = articleRepository.getReferenceById(dto.id());
            if (dto.title() != null){article.setTitle(dto.title());}
            if (dto.content() != null){article.setContent(dto.content());}
            article.setHashtag(dto.hashtag());
            return ResponseEntity.ok("게시글 수정이 성공했습니다.");
//        articleRepository.save(article); #-- 알아서 감지하므로 명시하지 않아도 된다.
        }
        catch (EntityNotFoundException e){
            log.warn("게시글 정보를 찾지 못했습니다.");
            return ResponseEntity.badRequest().body("존재하지 않은 게시글입니다.");
        }
    }

    // delete
    public ResponseEntity<String> deleteArticle(long articleId) {
        try{
            Article article = articleRepository.getReferenceById(articleId);
            articleRepository.deleteById(articleId);
            return ResponseEntity.ok("게시글이 삭제 되었습니다.");
        }
        catch (EntityNotFoundException e){
            log.warn("게시글 정보를 찾지 못했습니다.");
            return ResponseEntity.badRequest().body("게시글 정보를 찾지 못했습니다.");
        }
    }

    @Transactional(readOnly = true)
    public List<ArticleResponse> searchMyArticle(SearchType searchType, String keyword){
        if(searchType == null || keyword == null || keyword.isBlank()){
            return articleRepository.findAll().stream().map(ArticleDto::from).map(ArticleResponse::from).collect(Collectors.toList());
        }
        switch (searchType){
            case TITLE -> {
                return articleRepository.findByTitleContaining(keyword).stream().map(ArticleDto::from).map(ArticleResponse::from).collect(Collectors.toList());
            }
            case CONTENT-> {
                return articleRepository.findByContentContaining(keyword).stream().map(ArticleDto::from).map(ArticleResponse::from).collect(Collectors.toList());
            }
            case ID -> {
                return articleRepository.findByUserAccount_UserIdContaining(keyword).stream().map(ArticleDto::from).map(ArticleResponse::from).collect(Collectors.toList());
            }
            case NICKNAME -> {
                return articleRepository.findByUserAccount_NicknameContaining(keyword).stream().map(ArticleDto::from).map(ArticleResponse::from).collect(Collectors.toList());
            }
            case HASHTAG -> {
                return articleRepository.findByHashtag("#" + keyword).stream().map(ArticleDto::from).map(ArticleResponse::from).collect(Collectors.toList());
            }
        }
        return null;
    }


    /* --- ----- ---- 리팩토링 중 --- -*/


//    @Transactional(readOnly = true)
//    public Page<ArticleDto> searchArticles(SearchType searchType, String keyword, Pageable pageable) {
//        if (searchType == null || keyword == null || keyword.isBlank()){
//            return articleRepository.findAll(pageable).map(ArticleDto::from);
//        }
//        switch (searchType){
//            case TITLE -> articleRepository.findByTitleContaining(keyword, pageable).map(ArticleDto::from);
//            case CONTENT-> articleRepository.findByContentContaining(keyword, pageable).map(ArticleDto::from);
//            case ID -> articleRepository.findByUserAccount_UserIdContaining(keyword, pageable).map(ArticleDto::from);
//            case NICKNAME -> articleRepository.findByUserAccount_NicknameContaining(keyword, pageable).map(ArticleDto::from);
//            case HASHTAG -> articleRepository.findByHashtag("#" + keyword, pageable).map(ArticleDto::from);
//        };
//        return Page.empty();
//    }

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

}
