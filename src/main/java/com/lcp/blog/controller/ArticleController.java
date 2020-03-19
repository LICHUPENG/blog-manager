package com.lcp.blog.controller;

import com.lcp.blog.common.utils.AppException;
import com.lcp.blog.common.utils.Result;
import com.lcp.blog.entity.Article;
import com.lcp.blog.entity.form.ArticleForm;
import com.lcp.blog.entity.form.group.ModifyArticle;
import com.lcp.blog.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@RequestMapping("/user/article")
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/list")
    public Result getlist(Integer status, String title) {
        if (status == null) {
            return new Result("状态不能为空!", AppException.FORM_INVALID);
        }
        List<Article> list = this.articleService.getList(status, title);
        return new Result<>(list);
    }

    @PostMapping("/create")
    public Result create(@Validated ArticleForm form) {
        this.articleService.createArticle(form);
        return new Result("保存文章成功！");
    }

    @DeleteMapping("/delete")
    public Result delete(String article_id) {
        if (StringUtils.isBlank(article_id)) {
            throw new AppException("article_id不能为空！", AppException.FORM_INVALID);
        }
        this.articleService.delete(article_id);
        return new Result("删除文章成功!");
    }

    @PutMapping("/modify")
    public Result modify(@Validated({ModifyArticle.class}) ArticleForm form) {
        this.articleService.modify(form);
        return new Result("修改成功！");
    }

    @GetMapping("/info")
    public Result info(String article_id) {
        if (StringUtils.isBlank(article_id)) {
            throw new AppException("article_id不能为空！", AppException.FORM_INVALID);
        }
        return new Result<>(this.articleService.info(article_id));
    }

}
