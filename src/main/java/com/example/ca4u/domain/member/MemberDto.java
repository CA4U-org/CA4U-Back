package com.example.ca4u.domain.member;

import lombok.*;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private Long id;
    private String name;
    private String email;
    private String picture;

    public static MemberDto of(Member member){
        //아이디는 지워야 할 수도 있음
        return MemberDto.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .picture(member.getPicture())
                .build();
    }
}
