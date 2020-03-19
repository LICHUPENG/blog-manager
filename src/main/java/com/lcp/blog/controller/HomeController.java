package com.lcp.blog.controller;

import com.lcp.blog.common.utils.Result;
import com.lcp.blog.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@RequestMapping("/article")
public class HomeController {

    private final ArticleService articleService;

    @GetMapping("/list")
    public Result getAllUserArticle (String user_id) {
        return new Result<>(this.articleService.getAllUserArticle());
    }
}
