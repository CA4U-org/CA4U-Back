package com.example.ca4u.domain.article.dto;

import com.example.ca4u.domain.article.Article;
import com.example.ca4u.domain.article.articlePhoto.ArticlePhoto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class ArticleResponseDto {
    private Long articleId; //게시글아이디
    private String title; //게시글제목
    private String content; //게시글내용
    private List<String>  imgUrl; //게시글이미지주소
    private String author; //작성자명
    private LocalDateTime updDt; //게시글업데이트날짜

    public static ArticleResponseDto of(Article article){
        return ArticleResponseDto.builder()
                .articleId(article.getId())
                .title(article.getArticleNm())
                .content(article.getArticleDesc())
                .imgUrl(article.getArticlePhotoList().stream().map(ArticlePhoto::getImgUrl).toList()) // 첫번째 이미지만 가져옴
                .author(article.getMember().getNickname())
                .updDt(article.getUpdatedAt())
                .build();
    }
}
