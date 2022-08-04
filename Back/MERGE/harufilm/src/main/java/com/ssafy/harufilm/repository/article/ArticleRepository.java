package com.ssafy.harufilm.repository.article;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.harufilm.entity.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer>{

    List<Article> findAllByUserpid(int userpid); //userpid가 작성한 글 리스트 반환
    
    @Query("select a from Article a where a.userpid = (select s.subto from Subscribe s where s.subfrom = :userpid )")
    List<Article> articleList(@Param("userpid") int userpid); // 내가 구독한 사람들의 글 리스트 반환
    
    Optional<Article> findByArticleidx(int articleidx); // 글 하나의 정보 반환
}
