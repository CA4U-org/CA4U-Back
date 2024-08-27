package com.example.ca4u.domain.category;

import com.example.ca4u.domain.guild.Guild;
import com.example.ca4u.domain.guild.dto.GuildCateDto;
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
    //카테고리 관련
    private String imgUrl; //카테고리 이미지 주소
    private String categoryNm; //카테고리 이름
    private String categoryDesc; //카테고리 설명

    //길드 관련
    private List<GuildCateDto> guildList; //카테고리에 속한 길드들

    public static CategoryGuildResponseDto of(Category category, List<Guild> guildList){
        return CategoryGuildResponseDto.builder()
                .imgUrl(category.getImgUrl())
                .categoryNm(category.getCategoryNm())
                .categoryDesc(category.getCategoryDesc())
                .guildList(guildList.stream().map(GuildCateDto::of).toList())
                .build();
    }
}
