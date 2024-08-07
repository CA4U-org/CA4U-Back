package com.example.ca4u.domain.guild;

import com.example.ca4u.apiResponse.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Guild API", description = "길드(동아리,학회)에 대한 요청을 담당하는 API입니다.")
@RequestMapping("/api/v1/guilds")
@RequiredArgsConstructor
@RestController
public class GuildController {
    private final GuildService guildService;

    @Operation(summary = "길드 상세 조회", description = "길드의 상세페이지 정보를 전달합니다.", parameters = {
            @Parameter(name = "keyword", description = "검색어", in = ParameterIn.PATH)
    })
    @GetMapping("/{guild_id}")
    public ApiResponse<GuildReponseDto> getGuild(@PathVariable long guildId){
        return ApiResponse.ok(guildService.getGuildSpec(guildId));
    }

}
