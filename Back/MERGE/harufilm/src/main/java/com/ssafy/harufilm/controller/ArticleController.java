package com.ssafy.harufilm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.harufilm.common.ErrorResponseBody;
import com.ssafy.harufilm.common.MessageBody;
import com.ssafy.harufilm.dto.article.ArticleRequestDto;
import com.ssafy.harufilm.service.article.ArticleService;


@RestController
@RequestMapping("/api/article")
public class ArticleController {
    
    @Autowired
    ArticleService articleService;

    @PutMapping("")
    public ResponseEntity<?> setlikey(@RequestBody ArticleRequestDto articleRequestDto){
        try{
            articleService.articleSave(articleRequestDto);
        }catch(Exception e){
            return ResponseEntity.status(500).body(ErrorResponseBody.of(500, false,
            "Interanl Server Error, 글 저장 실패"));
        }
        return ResponseEntity.status(200).body(MessageBody.of(true, "글 저장 성공"));
    }

}
