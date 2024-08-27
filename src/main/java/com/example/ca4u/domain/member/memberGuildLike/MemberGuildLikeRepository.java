package com.example.ca4u.domain.member.memberGuildLike;

import com.example.ca4u.domain.guild.Guild;
import com.example.ca4u.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberGuildLikeRepository extends JpaRepository<MemberGuildLike, Long> {

    //아래 메서드들이 잘 작동하는지 확인해볼 필요가 있음.
    boolean existsByGuildAndMember(Guild guild, Member member);
    void deleteByGuildAndMember(Guild guild, Member member);
}
