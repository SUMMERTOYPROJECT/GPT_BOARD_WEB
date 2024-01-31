package com.jyp_board.board_app.domain;

import java.util.LinkedHashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Table(indexes = {
    @Index(columnList = "title"),
    @Index(columnList = "hashtag"),
    @Index(columnList = "createAt"),
    @Index(columnList = "createBy")
})
//@EntityListeners(AuditingEntityListener.class)
@Entity
public class Article extends BaseEntityConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @Column(nullable = false)private String title; // 제목
    @Setter @Column(nullable = false, length = 10000)private String content; // 본문
    @Setter private String hashtag; // 해시태그

    @ToString.Exclude
    @OrderBy("id")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();
//
//    @CreatedDate @Column(nullable = false)private LocalDateTime createAt; // 생성 일시
//    @CreatedBy @Column(nullable = false, length = 100) private String createBy;  //생성자
//    @LastModifiedDate @Column(nullable = false) private LocalDateTime modifiedAt; //수정 일시
//    @LastModifiedBy @Column(nullable = false, length = 100) private String modifiedBy; //수정자

    protected Article(){}
    
    private Article(String title, String content, String hashtag){
        this.title = title;
        this.content= content;
        this.hashtag = hashtag;
    }

    public static Article of(String title, String content, String hashtag){
        return new Article(title, content, hashtag);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Article other = (Article) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    
}
