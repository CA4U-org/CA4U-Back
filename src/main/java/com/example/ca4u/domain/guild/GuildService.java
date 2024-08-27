package com.example.ca4u.domain.guild;

import com.example.ca4u.config.auth.dto.LoginUser;
import com.example.ca4u.domain.article.Article;
import com.example.ca4u.domain.article.ArticleRepository;
import com.example.ca4u.domain.article.articleJunkReport.ArticleJunkReport;
import com.example.ca4u.domain.article.articleJunkReport.ArticleJunkReportRepository;
import com.example.ca4u.domain.article.dto.ArticleResponseDto;
import com.example.ca4u.domain.guild.album.Album;
import com.example.ca4u.domain.guild.album.AlbumDto;
import com.example.ca4u.domain.guild.dto.GuildArticleResponseDto;
import com.example.ca4u.domain.guild.dto.GuildNoticeResponseDto;
import com.example.ca4u.domain.guild.dto.GuildRankResponseDto;
import com.example.ca4u.domain.guild.dto.GuildReponseDto;
import com.example.ca4u.domain.guild.guildThumbnail.GuildThumbnail;
import com.example.ca4u.domain.guildHashtag.GuildHashtag;
import com.example.ca4u.domain.hashtag.Hashtag;
import com.example.ca4u.domain.member.Member;
import com.example.ca4u.domain.member.MemberRepository;
import com.example.ca4u.domain.member.memberGuildCert.MemberGuildCert;
import com.example.ca4u.domain.member.memberGuildCert.MemberGuildCertRepository;
import com.example.ca4u.domain.member.memberGuildLike.MemberGuildLike;
import com.example.ca4u.domain.member.memberGuildLike.MemberGuildLikeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GuildService {
    private final GuildRepository guildRepository; //길드 dao
    private final ArticleRepository articleRepository; //게시글 dao
    private final ArticleJunkReportRepository articleJunkReportRepository; //게시글 신고 dao
    private final MemberRepository memberRepository;  //멤버 dao
    private final MemberGuildLikeRepository memberGuildLikeRepository; //즐겨찾기 dao
    private final MemberGuildCertRepository memberGuildCertRepository; //길드회원인증 dao

    //특정 길드 상세페이지 정보를 return 하는 메서드
    //사용자 불러오기.. memberId..
    public GuildReponseDto getGuildSpec(Long guildId, Long memberId){
        Guild guild = guildRepository.findById(guildId).orElseThrow(() -> new EntityNotFoundException("해당 길드가 없습니다. id=" + guildId));
        List<Hashtag> HashtagList = guild.getGuildHashtagList().stream().map(GuildHashtag::getHashtag).toList(); //길드해시태그들 map으로 불러오는 부분이 불안함(N+1문제인가?)
        List<GuildThumbnail> guildThumbnails = guild.getGuildThumbnailList();

        //옵셔널로 조회 첫번째 게시글을 조회
        //Optional<Article> optionalArticle = guild.getArticleList().stream().findFirst();
     /*   if(optionalArticle.isPresent()) {
            Article article = optionalArticle.get();
        } else {
            Article article = new Article();
        }
     */

        Article article = guild.getArticleList().get(0);

        //사용자가 길드를 즐겨찾기 했는지 여부 개발하기 likeParam
        boolean like = memberGuildLikeRepository.existsByGuildAndMember(guild, memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException("해당 멤버가 없습니다. id=" + memberId)));
        boolean cert = memberGuildCertRepository.existsByGuildAndMember(guild, memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException("해당 멤버가 없습니다. id=" + memberId)));

        return GuildReponseDto.of(guild, guildThumbnails, HashtagList, article, like, cert);
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

    //인증 회원수를 기준으로 길드 랭킹을 return 하는 메서드
    public List<GuildRankResponseDto> getGuildRanksByCertUser() {
        List<Guild> guildList = guildRepository.findTop3GuildOrderByCertUserNum();
        return guildList.stream().map(GuildRankResponseDto::of).toList();
    }

    //길드 공지사항 리스트를 return 하는 메서드
    public List<GuildNoticeResponseDto> getGuildNotices() {
        //전체 ID 조회 후 랜덤으로 섞기
        List<Long> allIds = guildRepository.findAllIds();
        Collections.shuffle(allIds);
        //랜덤으로 10개만 뽑기
        List<Long> randomIds = allIds.subList(0, 10);
        //랜덤으로 뽑은 ID로 길드 조회
        List<Guild> randomGuilds = guildRepository.findAllById(randomIds);
        return randomGuilds.stream().map(GuildNoticeResponseDto::of).toList();
    }

    public void reportGuildJunkArticle(long articleId, Long memberId, String reportDesc) {
        //게시글 조회
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new EntityNotFoundException("해당 게시글이 없습니다. id=" + articleId));
        //멤버 조회
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException("해당 멤버가 없습니다. id=" + memberId));

        //신고내용 저장
        ArticleJunkReport articleJunkReport = ArticleJunkReport.of(article, member, reportDesc); //엔티티 그대로 사용하고 있는데 dto로 받아서 변환할까
        articleJunkReportRepository.save(articleJunkReport);
    }

    //즐겨찾기 목록에 추가
    public void likeOrDeleteBookMark(long guildId, Long memberId) {
        //길드 조회
        Guild guild = guildRepository.findById(guildId).orElseThrow(() -> new EntityNotFoundException("해당 길드가 없습니다. id=" + guildId));
        //멤버 조회
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException("해당 멤버가 없습니다. id=" + memberId));

        //즐겨찾기 목록에 있다면 추가 없다면 삭제
        if(memberGuildLikeRepository.existsByGuildAndMember(guild, member)) {
            memberGuildLikeRepository.deleteByGuildAndMember(guild, member);
        } else {
            MemberGuildLike memberGuildLike = new MemberGuildLike(guild, member); //생성자 방식
            memberGuildLikeRepository.save(memberGuildLike);
        }
    }

    public void certOrDeleteGuild(long guildId, Long id) {
        //길드 조회
        Guild guild = guildRepository.findById(guildId).orElseThrow(() -> new EntityNotFoundException("해당 길드가 없습니다. id=" + guildId));
        //멤버 조회
        Member member = memberRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("해당 멤버가 없습니다. id=" + id));

        //길드인증 목록에 있다면 추가 없다면 삭제 &&  //반정규화 처리 (길드 테이블에 인증회원수를 +1 또는 -1 조절하기
        if(memberGuildCertRepository.existsByGuildAndMember(guild, member)) {
            //길드인증목록에서 삭제
            memberGuildCertRepository.deleteByGuildAndMember(guild, member);
            //길드인증회원수 -1
            guild.decreaseGuildNum();
            guildRepository.save(guild);
        } else {
            //길드인증목록에 추가
            MemberGuildCert memberGuildCert = new MemberGuildCert(member, guild);  //생성자 방식
            memberGuildCertRepository.save(memberGuildCert);
            //길드인증회원수 +1
            guild.increaseGuildNum();
            guildRepository.save(guild);
        }
    }
}
