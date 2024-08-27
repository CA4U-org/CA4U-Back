package com.example.ca4u.domain.guild.dto;

import com.example.ca4u.domain.guild.Guild;
import com.example.ca4u.domain.guildHashtag.GuildHashtag;
import com.example.ca4u.domain.hashtag.HashtagDto;
import lombok.*;

import java.util.List;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class GuildCateDto {
    private Long guildId;
    private String guildNm;
    private String guildBriefDesc;
    private String ImgUrl;
    private List<HashtagDto> hashtagList;

    public static GuildCateDto of(Guild guild) {
        // M:M 관계 테이블에서 길드 -> 길드_해쉬태그 -> 해쉬태그를 차례로 조회하고 dto로 변환시켜준다.

        List<GuildHashtag> guildHashList = guild.getGuildHashtagList();
        List<HashtagDto> hashtagList = guildHashList.stream()
                .map(GuildHashtag::getHashtag)
                .map(HashtagDto::of)
                .toList();

    return GuildCateDto.builder()
                    .guildId(guild.getId())
                    .guildNm(guild.getGuildNm())
                    .guildBriefDesc(guild.getGuildBriefDesc())
                    .ImgUrl(guild.getLogoImgUrl())
                    .hashtagList(hashtagList)
                    .build();
    }
}
