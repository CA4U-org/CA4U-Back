package com.example.ca4u.domain.guild.dto;

import com.example.ca4u.domain.article.Article;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class GuildArticleResponseDto {
    private Long articleId; //게시글아이디
    private String title; //게시글제목
    private String content; //게시글내용
    private String imgUrl; //게시글이미지주소
    private LocalDateTime updDt; //게시글업데이트날짜
    private String author; //작성자명

    public static GuildArticleResponseDto of(Article article){
        return GuildArticleResponseDto.builder()
                .articleId(article.getId())
                .title(article.getArticleNm())
                .content(article.getArticleDesc())
                .imgUrl(article.getArticlePhotoList().get(0).getImgUrl()) // 첫번째 이미지만 가져옴
                .updDt(article.getUpdatedAt())
                .author(article.getMember().getNickname())
                .build();
    }
}
