package com.example.ca4u.domain.member;

import com.example.ca4u.apiResponse.ApiResponse;
import com.example.ca4u.config.auth.SessionMember;
import com.example.ca4u.config.auth.dto.LoginUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Member API", description = "Member(회원정보)와 관련된 API입니다.")
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberService memberService;

    @Operation(summary = "유저 상세 조회", description = "유저의 마이페이지 정보를 전달합니다.", parameters = {
            @Parameter(name = "member_id", description = "길드아이디", in = ParameterIn.PATH)
    })
    @GetMapping("/")        //이렇게 세션멤버를 가져와서 하는게 맞는지는 모르겠음 (나중에 jwt 토큰으로 바꾸면 어떻게될런지)
    public ApiResponse<MemberDto> getGuild(@LoginUser SessionMember member){
        //요청한 memberId가 세션에 저장된 memberId와 일치하는지 확인해야함

        return ApiResponse.ok(memberService.getMemberSpec(member.getId()));
    }
}

