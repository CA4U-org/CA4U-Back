package com.example.ca4u.domain.member;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberDto getMemberSpec(long memberId){
        Member member =  memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException("해당 멤버가 없습니다."));
        return MemberDto.of(member);
    }
}
