package com.example.ca4u.domain.guild;

import com.example.ca4u.apiResponse.ApiResponse;
import com.example.ca4u.domain.article.dto.ArticleResponseDto;
import com.example.ca4u.domain.guild.album.Album;
import com.example.ca4u.domain.guild.album.AlbumDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Guild API", description = "길드(동아리,학회)에 대한 요청을 담당하는 API입니다.")
@RequestMapping("/api/v1/guilds")
@RequiredArgsConstructor
@RestController
public class GuildController {
    private final GuildService guildService;

    @Operation(summary = "길드 상세 조회", description = "길드의 상세페이지 정보를 전달합니다.", parameters = {
            @Parameter(name = "guildId", description = "길드아이디", in = ParameterIn.PATH)
    })
    @GetMapping("/{guildId}")
    public ApiResponse<GuildReponseDto> getGuild(@PathVariable long guildId){
        return ApiResponse.ok(guildService.getGuildSpec(guildId));
    }

    @Operation(summary = "길드 게시글보기", description = "길드 게시글 정보를 전달합니다.", parameters = {
            @Parameter(name = "guildId", description = "길드아이디", in = ParameterIn.PATH)
    })
    @GetMapping("/{guildId}/articles")
    public ApiResponse<List<GuildArticleResponseDto>> getGuildArticles(@PathVariable long guildId){
        return ApiResponse.ok(guildService.getGuildArticles(guildId));
    }

    @Operation(summary = "길드 특정 게시글 상세보기", description = "길드의 특정 게시글 정보를 전달합니다.", parameters = {
            @Parameter(name = "article_id", description = "게시글아이디", in = ParameterIn.PATH)
    })
    @GetMapping("/guilds/articles/{articleId}")
    public ApiResponse<ArticleResponseDto> getGuildArticle(@PathVariable long articleId){
        return ApiResponse.ok(guildService.getGuildArticle(articleId));
    }

    @Operation(summary = "길드 앨범보기", description = "길드 앨범 정보를 전달합니다.", parameters = {
            @Parameter(name = "guildId", description = "길드아이디", in = ParameterIn.PATH)
    })
    public ApiResponse<List<AlbumDto>> getGuildAlbum(@PathVariable long guildId){
        return ApiResponse.ok(guildService.getGuildAlbums(guildId));
    }
}
