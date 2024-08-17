package com.example.ca4u.domain.guild.album;

import com.example.ca4u.domain.base.BaseEntity;
import com.example.ca4u.domain.guild.Guild;
import com.example.ca4u.domain.guild.albumPhoto.AlbumPhoto;
import com.example.ca4u.domain.member.Member;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Album extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guild_id")
    private Guild guild;

    private String albumNm;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "album")
    private List<AlbumPhoto> photoList = new ArrayList<>();
}
