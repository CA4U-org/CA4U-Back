package com.example.ca4u.domain.article.articleJunkReport;

import com.example.ca4u.domain.article.Article;
import com.example.ca4u.domain.base.BaseEntity;
import com.example.ca4u.domain.guild.Guild;
import com.example.ca4u.domain.member.Member;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity(name = "ARTICLE_JUNK_REPORT")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ArticleJunkReport extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "report_desc")
    private String reportDesc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public static ArticleJunkReport of(Article article, Member member, String reportDesc){
        return ArticleJunkReport.builder()
                .article(article)
                .member(member)
                .reportDesc(reportDesc)
                .build();
    }
}
