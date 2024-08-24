package com.example.ca4u.domain.guild.dto;

import com.example.ca4u.domain.guild.Guild;
import lombok.*;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class GuildRankResponseDto {
    private Long guildId; //길드아이디
    private String guildNm; //길드이름
    private String guildBriefDesc; //길드간략설명
    private String logoImgUrl; //길드 프로필 이미지 주소

    public static GuildRankResponseDto of(Guild guild){
        return GuildRankResponseDto.builder()
                .guildId(guild.getId())
                .guildNm(guild.getGuildNm())
                .guildBriefDesc(guild.getGuildBriefDesc())
                .logoImgUrl(guild.getLogoImgUrl())
                .build();
    }
}
