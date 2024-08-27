package com.example.ca4u.domain.category;

import com.example.ca4u.domain.base.BaseEntity;
import com.example.ca4u.domain.guild.Guild;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity(name = "CATEGORY")
public class Category extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "category_nm", nullable = false)
    private String categoryNm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id")
    private Category parent;

    //카테고리 타입 - (게시글을 보여주기 vs 카테고리들 다시 보여주기) 카테고리 안에 카테고리..
    @Column(name = "category_type")
    private String categoryType;

    @Column(name = "category_desc")
    private String categoryDesc;

    @Column(name = "img_url")
    private String imgUrl;

    @OneToMany(mappedBy = "category")
    private List<Guild> guildList = new ArrayList<>();
}
