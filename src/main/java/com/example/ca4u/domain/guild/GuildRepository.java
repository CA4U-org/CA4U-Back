package com.example.ca4u.domain.guild;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GuildRepository extends JpaRepository<Guild, Long> {
    //길드인증 회원수 top3 길드 조회
    @Query("SELECT g FROM Guild g " +
            "ORDER BY g.guildNum DESC LIMIT 3")
    List<Guild> findTop3GuildOrderByCertUserNum();

    //길드 아이디만 모두 조회 (길드 모집공고글 랜덤 return에 활용)
    @Query("SELECT g.id FROM Guild g")
    List<Long> findAllIds();
}
