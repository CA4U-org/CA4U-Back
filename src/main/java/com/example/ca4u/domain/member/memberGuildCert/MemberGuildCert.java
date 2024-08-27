package com.example.ca4u.domain.member.memberGuildCert;

import com.example.ca4u.domain.base.BaseEntity;
import com.example.ca4u.domain.guild.Guild;
import com.example.ca4u.domain.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity(name = "MEMBER_GUILD_CERT")
public class MemberGuildCert extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guild_id")
    private Guild guild;

    public MemberGuildCert(Member member, Guild guild) {
        this.member = member;
        this.guild = guild;
    }
}
