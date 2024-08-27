package com.example.ca4u.domain.member.memberGuildLike;

import com.example.ca4u.domain.base.BaseEntity;
import com.example.ca4u.domain.guild.Guild;
import com.example.ca4u.domain.member.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
@Entity(name = "MEMBER_GUILD_LIKE")
public class MemberGuildLike extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guild_id")
    private Guild guild;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public MemberGuildLike(Guild guild, Member member) {
        this.guild = guild;
        this.member = member;
    }
}
