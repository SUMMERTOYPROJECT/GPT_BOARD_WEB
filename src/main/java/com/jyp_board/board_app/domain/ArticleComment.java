package com.jyp_board.board_app.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.userdetails.UserCache;

@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = {
    @Index(columnList = "content"),
    @Index(columnList = "createAt"),
    @Index(columnList = "createBy")
})
//@EntityListeners(AuditingEntityListener.class)
@Entity
public class ArticleComment extends BaseEntityConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @ManyToOne(optional = false) private UserAccount userAccount;
    @Setter @ManyToOne(optional = false)private Article article;
    @Setter @Column(nullable = false, length = 500)private String content;

//    @CreatedDate @Column(nullable = false)private LocalDateTime createAt; // 생성 일시
//    @CreatedBy @Column(nullable = false, length = 100) private String createBy;  //생성자
//    @LastModifiedDate @Column(nullable = false) private LocalDateTime modifiedAt; //수정 일시
//    @LastModifiedBy @Column(nullable = false, length = 100) private String modifiedBy; //수정자

    
    private ArticleComment(Article article, UserAccount userAccount, String content){
        this.article = article;
        this.userAccount = userAccount;
        this.content = content;
    }

    public static ArticleComment of(Article article, UserAccount userAccount, String content){
        return new ArticleComment(article, userAccount, content);
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
        ArticleComment other = (ArticleComment) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    

    
}
