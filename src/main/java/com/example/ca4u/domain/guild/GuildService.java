package com.example.ca4u.domain.guild;

import com.example.ca4u.domain.article.Article;
import com.example.ca4u.domain.guild.guildThumbnail.GuildThumbnail;
import com.example.ca4u.domain.guildHashtag.GuildHashtag;
import com.example.ca4u.domain.hashtag.Hashtag;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GuildService {
    private final GuildRepository guildRepository;

    //특정 길드 상세페이지 정보를 return 하는 메서드
    public GuildReponseDto getGuildSpec(Long guildId){
        Guild guild = guildRepository.findById(guildId).orElseThrow(() -> new EntityNotFoundException("해당 길드가 없습니다. id=" + guildId));
        List<Hashtag> HashtagList = guild.getGuildHashtagList().stream().map(GuildHashtag::getHashtag).toList();
        List<GuildThumbnail> guildThumbnails = guild.getGuildThumbnailList();
        Article article = guild.getArticleList().get(5);
        GuildReponseDto.of(guild, guildThumbnails, HashtagList, article,true, true);


        return new GuildReponseDto();
    }
}
