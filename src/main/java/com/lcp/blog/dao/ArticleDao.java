package com.lcp.blog.dao;


import com.lcp.blog.common.annotation.DateTimeFields;
import com.lcp.blog.common.dao.BaseDao;
import com.lcp.blog.common.utils.AppFunction;
import com.lcp.blog.entity.Article;
import com.lcp.blog.mapper.ArticleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lcp
 */
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ArticleDao extends BaseDao<Article> {

    /**
     * 草稿
     */
    public static final int DRAFT = 0;

    /**
     * 发布
     */
    public static final int RELEASE = 1;

    /**
     * 回收站
     */
    public static final int DELETE = -1;

    private final ArticleMapper mapper;

    @Override
    protected Mapper<Article> getMapper() {
        return this.mapper;
    }


    @DateTimeFields(fields = {"created_time", "updated_time"})
    public List<Map<String, Object>> getList() {
        Example example = new Example(Article.class);
        example.createCriteria()
                .andNotEqualTo("status", DELETE)
                .andNotEqualTo("status", DRAFT);
        return this.mapper.selectByExample(example).stream().map(
                AppFunction::beanToMap
        ).collect(Collectors.toList());
    }
}
