package com.jyp_board.board_app.repository;

import com.jyp_board.board_app.domain.QArticleComment;
import com.jyp_board.board_app.domain.UserAccount;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jyp_board.board_app.domain.ArticleComment;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

//@RepositoryRestResource
public interface ArticleCommentRepository extends
        JpaRepository<ArticleComment, Long>,
        QuerydslPredicateExecutor<ArticleComment>, // 모든 필드에 대한 검색 기능을 추가해줌
        QuerydslBinderCustomizer<QArticleComment>
{
    List<ArticleComment>findByArticle_Id(Long articleId);
    List<ArticleComment>findArticleCommentsByUserAccount(UserAccount userAccount);
    @Override
    default void customize(QuerydslBindings bindings, QArticleComment root){
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.createAt, root.createBy, root.content);
        bindings.bind(root.createBy).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createAt).first(DateTimeExpression::eq);
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
    }
}
