package com.example.ca4u.domain.guild;

import com.example.ca4u.domain.article.Article;
import com.example.ca4u.domain.guild.guildThumbnail.GuildThumbnail;
import com.example.ca4u.domain.guildHashtag.GuildHashtag;
import com.example.ca4u.domain.hashtag.Hashtag;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class GuildReponseDto {
    private Long guildId; //길드아이디
    private List<String> thumbImgUrl; //길드썸네일이미지 주소
    private String logoImgUrl; //길드로고이미지 주소
    private Boolean like; //사용자 즐겨찾기 여부
    private Boolean cert; //사용자 소속인증 여부
    private String guildNm; //길드이름
    private String guildBriefDesc; //길드간략설명
    private List<Map.Entry<String, String>> hashtags; //길드 해시태그들 <해시태그 이름, 해시태그 이미지 주소>
    private String targetPeopleDesc; //선발대상설명
    private String targetCycleDesc; //선발주기설명
    private String applyDesc; //지원방법설명
    private String actDayDesc; //활동요일설명
    private String locationDesc; //활동장소_설명 (동아리방 위치 등)
    private Integer guildNum; //회원수
    private String costDesc; //회비설명
    private LocalDateTime updDt; //업데이트날짜
    private Map.Entry<String, String> article; //동아리 최신 게시글 1개 <제목, 게시글내용>
    private String specDesc; //길드상세설명(About  길드)
    private String recruitDesc; //모집공고글

    public static GuildReponseDto of(Guild guild,
                                     List<GuildThumbnail> guildThumbnails,
                                     List<Hashtag> Hashtags,
                                     Article article,
                                     Boolean likeParam,
                                     Boolean certParam){

        return GuildReponseDto.builder()
                .guildId(guild.getId())
                /*.thumbImgUrl(guildThumbnails.stream()
                        .map(GuildThumbnail::getImgUrl)
                        .toList())*/
                .logoImgUrl(guild.getLogoImgUrl())
                .like(likeParam)
                .cert(certParam)
                .guildNm(guild.getGuildNm())
                .guildBriefDesc(guild.getGuildBriefDesc())
                /*.hashtags(guildHashtags. stream()
                        .map(hashtag -> Map.entry(hashtag.get, hashtag.getImageUrl()))
                        .toList())*/
                .targetPeopleDesc(guild.getTargetPeopleDesc())
                .targetCycleDesc(guild.getTargetCycleDesc())
                .applyDesc(guild.getApplyDesc())
                .actDayDesc(guild.getActDayDesc())
                .locationDesc(guild.getLocationDesc())
                .guildNum(guild.getGuildNum())
                .costDesc(guild.getCostDesc())
                .updDt(guild.getUpdatedAt())
                .article(Map.entry(article.getArticleNm(), article.getArticleDesc()))
                .specDesc(guild.getSpecDesc())
                .recruitDesc(guild.getRecruitDesc())
                .build();

                //위에 주석 손보기 parameters
    }
}
