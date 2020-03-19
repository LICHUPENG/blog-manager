package com.lcp.blog.entity.form;

import com.lcp.blog.entity.form.group.CreateArticle;
import com.lcp.blog.entity.form.group.ModifyArticle;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author lcp
 */
@Data
public class ArticleForm {

    @NotBlank(groups = {ModifyArticle.class})
    private String article_id;

    @NotBlank
    @Size(max = 100, message = "文章题目不能超过100个字", groups = {CreateArticle.class})
    private String title;

    @NotBlank
    @Size(max = 8, message = "分类名不能超过8个字", groups = {CreateArticle.class})
    private String category;

    private String content;

    private List<String> tag;

    @NotNull
    private Integer status;
}
