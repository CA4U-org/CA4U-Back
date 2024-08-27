package com.example.ca4u.domain.guild;

import com.example.ca4u.apiResponse.ApiResponse;
import com.example.ca4u.config.auth.SessionMember;
import com.example.ca4u.config.auth.dto.LoginUser;
import com.example.ca4u.domain.article.dto.ArticleResponseDto;
import com.example.ca4u.domain.guild.album.AlbumDto;
import com.example.ca4u.domain.guild.dto.GuildArticleResponseDto;
import com.example.ca4u.domain.guild.dto.GuildNoticeResponseDto;
import com.example.ca4u.domain.guild.dto.GuildRankResponseDto;
import com.example.ca4u.domain.guild.dto.GuildReponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public ApiResponse<GuildReponseDto> getGuild(@PathVariable long guildId, @LoginUser SessionMember member){
        return ApiResponse.ok(guildService.getGuildSpec(guildId, member.getId()), "길드 상세 정보를 불러왔습니다.");
    }

    @Operation(summary = "길드 게시글보기", description = "길드 게시글 정보를 전달합니다.", parameters = {
            @Parameter(name = "guildId", description = "길드아이디", in = ParameterIn.PATH)
    })
    @GetMapping("/{guildId}/articles")
    public ApiResponse<List<GuildArticleResponseDto>> getGuildArticles(@PathVariable long guildId){
        return ApiResponse.ok(guildService.getGuildArticles(guildId), "길드에 속한 게시글 정보를 불러왔습니다.");
    }

    @Operation(summary = "길드 특정 게시글 상세보기", description = "길드의 특정 게시글 정보를 전달합니다.", parameters = {
            @Parameter(name = "article_id", description = "게시글아이디", in = ParameterIn.PATH)
    })
    @GetMapping("/guilds/articles/{articleId}")
    public ApiResponse<ArticleResponseDto> getGuildArticle(@PathVariable long articleId){
        return ApiResponse.ok(guildService.getGuildArticle(articleId), "게시글 상세 정보를 불러왔습니다.");
    }

    @Operation(summary = "길드 앨범보기", description = "길드 앨범 정보를 전달합니다.", parameters = {
            @Parameter(name = "guildId", description = "길드아이디", in = ParameterIn.PATH)
    })
    public ApiResponse<List<AlbumDto>> getGuildAlbum(@PathVariable long guildId){
        return ApiResponse.ok(guildService.getGuildAlbums(guildId), "길드 앨범 정보를 불러왔습니다.");
    }

    @Operation(summary = "동아리 랭킹 불러오기", description = "인증회원수가 많은 순서대로 동아리 랭킹 정보를 전달합니다.")
    @GetMapping("/ranks")
    public ApiResponse<List<GuildRankResponseDto>> getGuildRanks(){
        return ApiResponse.ok(guildService.getGuildRanksByCertUser(), "동아리 랭킹 정보를 불러왔습니다.");
    }

    @Operation(summary = "모집공고 리스트 불러오기", description = "모집공고에 게시될 길드 목록을 전달합니다.")
    @GetMapping("/notices")
    public ApiResponse<List<GuildNoticeResponseDto>> getGuildNotices(){
        return ApiResponse.ok(guildService.getGuildNotices(), "모집공고 리스트를 불러왔습니다.");
    }

    @Operation(summary = "길드 특정 게시글 신고하기", description = "길드의 특정 게시글을 신고합니다.", parameters = {
            @Parameter(name = "articleId", description = "게시글아이디", in = ParameterIn.PATH)
    })
    @PostMapping("/articles/{articleId}/junk")
    public ApiResponse<Void> reportGuildArticle(@PathVariable long articleId, @LoginUser SessionMember member, @RequestBody String reportDesc){
        guildService.reportGuildJunkArticle(articleId, member.getId(), reportDesc);
        return ApiResponse.ok(null, "신고가 완료되었습니다.");
    }

    @Operation(summary = "길드 즐겨찾기 추가, 해제", description = "특정 회원이 길드를 즐겨찾기 목록에 추가하거나 해제합니다.", parameters = {
            @Parameter(name = "guildId", description = "길드아이디", in = ParameterIn.PATH)
    })
    @PostMapping("/{guildId}/likes")
    public ApiResponse<Void> likeOrDeleteGuild(@PathVariable long guildId, @LoginUser SessionMember member){
        guildService.likeOrDeleteBookMark(guildId, member.getId());
        return ApiResponse.ok(null, "즐겨찾기 추가 또는 해제 완료되었습니다.");
    }

    @Operation(summary = "길드 인증 회원 추가, 해제", description = "특정 회원이 길드를 길드인증 목록에 추가하거나 해제합니다.", parameters = {
            @Parameter(name = "guildId", description = "길드아이디", in = ParameterIn.PATH)
    })
    @PostMapping("/{guildId}/cert")
    public ApiResponse<Void> CertOrDeleteGuild(@PathVariable long guildId, @LoginUser SessionMember member){
        guildService.certOrDeleteGuild(guildId, member.getId());
        return ApiResponse.ok(null, "길드 인증 또는 해제 완료되었습니다.");
    }
}
