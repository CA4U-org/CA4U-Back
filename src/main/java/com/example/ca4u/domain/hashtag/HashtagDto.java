package com.example.ca4u.domain.hashtag;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HashtagDto {
    private Long id;
    private String hashtagNm;
    private String imgUrl;

    public static HashtagDto of(Hashtag hashtag){
        return HashtagDto.builder()
                .id(hashtag.getId())
                .hashtagNm(hashtag.getHashtagNm())
                .imgUrl(hashtag.getImgUrl())
                .build();
    }
}
