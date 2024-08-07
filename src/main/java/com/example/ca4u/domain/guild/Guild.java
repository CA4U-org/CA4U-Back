package com.example.ca4u.domain.guild;

import com.example.ca4u.domain.article.Article;
import com.example.ca4u.domain.base.BaseEntity;
import com.example.ca4u.domain.category.Category;
import com.example.ca4u.domain.guild.guildThumbnail.GuildThumbnail;
import com.example.ca4u.domain.guildHashtag.GuildHashtag;
import com.example.ca4u.domain.hashtag.Hashtag;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Guild extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guild_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column
    private String recruitDesc;

    @Column
    private String guildNm;

    @Column
    private String guildBriefDesc;

    @Column
    private String targetPeopleDesc;

    @Column
    private String targetCycleDesc;

    @Column
    private String applyDesc;

    @Column
    private String actDayDesc;

    @Column
    private String locationDesc;

    @ColumnDefault("0")
    @Column
    private Integer guildNum;

    @Column
    private String costDesc;

    @Column
    private String specDesc;

    @Column
    private String logoImgUrl;

    @OneToMany(mappedBy = "guild")
    private List<GuildHashtag> guildHashtagList = new ArrayList<>();

    @OneToMany(mappedBy = "guild")
    private List<GuildThumbnail> guildThumbnailList = new ArrayList<>();

    @OneToMany(mappedBy = "guild")
    private List<Article> articleList = new ArrayList<>();
}
