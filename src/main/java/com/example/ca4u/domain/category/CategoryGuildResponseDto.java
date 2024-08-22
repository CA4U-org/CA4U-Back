package com.example.ca4u.domain.category;

import com.example.ca4u.domain.guild.Guild;
import com.example.ca4u.domain.hashtag.Hashtag;
import com.example.ca4u.domain.hashtag.HashtagDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryGuildResponseDto {
    private Long id; //길드아이디
    private String guildNm; //길드이름
    private String guildBriefDesc; //길드간략설명
    private String imgUrl; //길드 프로필 이미지 주소
    private List<HashtagDto> hashtagList; //길드 해시태그 리스트
}
