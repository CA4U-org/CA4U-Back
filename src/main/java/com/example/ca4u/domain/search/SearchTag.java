package com.example.ca4u.domain.search;

import com.example.ca4u.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity(name = "SEARCH_TAG")
public class SearchTag extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tag_nm")
    private String tagNm;
}
