package com.example.ca4u.domain.guild.dto;

import com.example.ca4u.domain.guild.Guild;
import lombok.*;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class GuildNoticeResponseDto {
     //길드아이디
    private Long guildId;
    //길드이름
    private String guildNm;
    // 모집공고 게시글제목
    private String title;
    // 모집공고 기한
    private String deadline;
    // 길드 이미지 로고 (공고 이미지, 로고 이미지인지 아니면 모집공고글에 올라온 사진인건지 물어봐야할듯)
    private String logoImgUrl;

    public static GuildNoticeResponseDto of(Guild guild){
        return GuildNoticeResponseDto.builder()
                .guildId(guild.getId())
                .guildNm(guild.getGuildNm())
                .title(guild.getRecruitDesc().substring(0, 20)) // 길드 모집공고 게시글 제목을 20자로 자름
                .deadline(guild.getTargetCycleDesc()) // 길드 모집공고 기한
                .logoImgUrl(guild.getLogoImgUrl())
                .build();
    }
}
