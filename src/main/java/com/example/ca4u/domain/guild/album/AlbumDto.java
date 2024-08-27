package com.example.ca4u.domain.guild.album;

import lombok.*;

import java.util.List;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class AlbumDto {
    private String albumNm;
    private List<String> phtoUrlList;

    public static AlbumDto of(Album album){
        return AlbumDto.builder()
                .albumNm(album.getAlbumNm())
                .phtoUrlList(album.getPhotoList().stream().map(albumPhoto -> albumPhoto.getImgUrl()).toList())
                .build();
    }
}
