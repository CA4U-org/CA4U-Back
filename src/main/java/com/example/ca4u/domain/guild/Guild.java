package com.example.ca4u.domain.guild;

import com.example.ca4u.domain.article.Article;
import com.example.ca4u.domain.base.BaseEntity;
import com.example.ca4u.domain.category.Category;
import com.example.ca4u.domain.guild.album.Album;
import com.example.ca4u.domain.guild.guildThumbnail.GuildThumbnail;
import com.example.ca4u.domain.guildHashtag.GuildHashtag;
import com.example.ca4u.domain.hashtag.Hashtag;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity(name = "GUILD")
public class Guild extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "recruit_desc")
    private String recruitDesc; //모집공고글

    @Column(name = "guild_nm")
    private String guildNm;  //길드이름

    @Column(name = "guild_brief_desc")
    private String guildBriefDesc; //길드간략설명

    @Column(name = "target_people_desc")
    private String targetPeopleDesc; //선발대상설명

    @Column(name = "target_cycle_desc")
    private String targetCycleDesc; //선발주기설명

    @Column(name = "apply_desc")
    private String applyDesc; //지원방법설명

    @Column(name = "act_day_desc")
    private String actDayDesc; //활동요일설명

    @Column(name = "location_desc")
    private String locationDesc; //활동장소_설명 (동아리방 위치 등)

    @ColumnDefault("0")
    @Column(name = "guild_num")
    private Integer guildNum; //인증회원수 (반정규화)

    @Column(name = "cost_desc")
    private String costDesc; //회비설명

    @Column(name = "spec_desc")
    private String specDesc; //길드상세설명(About  길드)

    @Column(name = "logo_img_url")
    private String logoImgUrl; //길드로고이미지 주소

    @OneToMany(mappedBy = "guild")
    private List<GuildHashtag> guildHashtagList = new ArrayList<>();

    @OneToMany(mappedBy = "guild")
    private List<GuildThumbnail> guildThumbnailList = new ArrayList<>();

    @OneToMany(mappedBy = "guild")
    private List<Article> articleList = new ArrayList<>();

    @OneToMany(mappedBy = "guild")
    private List<Album> albumList = new ArrayList<>();

    public void increaseGuildNum() {
        this.guildNum++;
    }

    public void decreaseGuildNum() {
        this.guildNum--;
    }
}
