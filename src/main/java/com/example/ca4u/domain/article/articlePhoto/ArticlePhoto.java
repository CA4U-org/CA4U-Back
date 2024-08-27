package com.example.ca4u.domain.article.articlePhoto;

import com.example.ca4u.domain.article.Article;
import com.example.ca4u.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity(name = "ARTICLE_PHOTO")
public class ArticlePhoto extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "img_url")
    private String imgUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;
}
