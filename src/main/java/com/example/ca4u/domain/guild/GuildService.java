package com.example.ca4u.domain.guild;

import com.example.ca4u.domain.article.Article;
import com.example.ca4u.domain.article.ArticleRepository;
import com.example.ca4u.domain.article.dto.ArticleResponseDto;
import com.example.ca4u.domain.guild.album.Album;
import com.example.ca4u.domain.guild.album.AlbumDto;
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
    private final ArticleRepository articleRepository;

    //특정 길드 상세페이지 정보를 return 하는 메서드
    //사용자 불러오기.. memberId..
    public GuildReponseDto getGuildSpec(Long guildId){
        Guild guild = guildRepository.findById(guildId).orElseThrow(() -> new EntityNotFoundException("해당 길드가 없습니다. id=" + guildId));
        List<Hashtag> HashtagList = guild.getGuildHashtagList().stream().map(GuildHashtag::getHashtag).toList(); //길드해시태그들 map으로 불러오는 부분이 불안함(N+1문제인가?)
        List<GuildThumbnail> guildThumbnails = guild.getGuildThumbnailList();
        Article article = guild.getArticleList().get(5);

        //사용자가 길드를 즐겨찾기 했는지 여부 개발하기 likeParam


        return GuildReponseDto.of(guild, guildThumbnails, HashtagList, article,true, true);
    }

    //특정 길드의 게시글 리스트 정보를 return 하는 메서드
    public List<GuildArticleResponseDto> getGuildArticles(long guildId) {
        Guild guild = guildRepository.findById(guildId).orElseThrow(() -> new EntityNotFoundException("해당 길드가 없습니다. id=" + guildId));
        List<Article> articleList = guild.getArticleList();
        return articleList.stream().map(GuildArticleResponseDto::of).toList();
    }

    //특정 길드의 한 게시글 리스트 정보를 return 하는 메서드
    public ArticleResponseDto getGuildArticle(long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new EntityNotFoundException("해당 게시글이 없습니다. id=" + articleId));
        return ArticleResponseDto.of(article);
    }

    //특정 길드의 앨범 리스트 정보를 return 하는 메서드
    public List<AlbumDto> getGuildAlbums(long guildId) {
        List<Album> albumList = guildRepository.findById(guildId).orElseThrow(() -> new EntityNotFoundException("해당 길드가 없습니다. id=" + guildId)).getAlbumList();
        return albumList.stream().map(AlbumDto::of).toList();
    }
}
