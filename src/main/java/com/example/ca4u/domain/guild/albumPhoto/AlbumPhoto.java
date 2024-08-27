package com.example.ca4u.domain.guild.albumPhoto;

import com.example.ca4u.domain.base.BaseEntity;
import com.example.ca4u.domain.guild.album.Album;
import com.example.ca4u.domain.member.Member;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity(name = "ALBUM_PHOTO")
public class AlbumPhoto extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    private Album album;

    @Column(name = "img_url")
    private String imgUrl;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
