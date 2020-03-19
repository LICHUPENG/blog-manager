package com.lcp.blog.service;

import com.lcp.blog.common.annotation.DateTimeFields;
import com.lcp.blog.common.utils.AppException;
import com.lcp.blog.common.utils.AppFunction;
import com.lcp.blog.common.utils.FastuuidUtil;
import com.lcp.blog.dao.ArticleDao;
import com.lcp.blog.dao.TagDao;
import com.lcp.blog.entity.Article;
import com.lcp.blog.entity.Tag;
import com.lcp.blog.entity.form.ArticleForm;
import com.lcp.blog.entity.form.group.CreateArticle;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import tk.mybatis.mapper.entity.Example;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lcp
 */
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ArticleService {

    private final ArticleDao articleDao;

    private final TagDao tagDao;

    @DateTimeFields(fields = {"created_time", "updated_time"})
    public List<Article> getList(Integer status, String title) {
        Example example = new Example(Article.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNoneBlank(title)) {
            criteria.andLike("title", title);
        }
        if (status == 2) {
            criteria.andEqualTo("status", ArticleDao.RELEASE);
        } else if (status == ArticleDao.DELETE) {
            criteria.andEqualTo("status", ArticleDao.DELETE);
        } else if (status == ArticleDao.DRAFT) {
            criteria.andEqualTo("status", ArticleDao.DRAFT);
        } else {
            criteria.andNotEqualTo("status", ArticleDao.DELETE);
        }

        return this.articleDao.selectByExample(example);
    }

    @Transactional(rollbackFor = Exception.class)
    public void createArticle(@Validated({CreateArticle.class}) ArticleForm form) {
        int now = AppFunction.getTime();
        String uuid = FastuuidUtil.genUUID();
        Article article = new Article();
        article.setTitle(form.getTitle());
        article.setArticle_id(uuid);
        article.setCategory(form.getCategory());
        article.setStatus(form.getStatus());
        article.setContent(form.getContent());
        article.setCreated_time(now);
        article.setUpdated_time(now);
        this.articleDao.insertSelective(article);

        List<String> list = form.getTag();
        if (list != null) {
            if (!list.isEmpty()) {
                for (String s : list) {
                    if (StringUtils.isNotBlank(s.trim())) {
                        Tag tag = new Tag();
                        tag.setTag_id(FastuuidUtil.genUUID());
                        tag.setArticle_id(uuid);
                        tag.setTag(s);
                        this.tagDao.insert(tag);
                    }
                }
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(String article_id) {
        Article article = this.articleDao.selectByPrimaryKey(article_id);
        article.setStatus(ArticleDao.DELETE);
        this.articleDao.updateByPrimaryKeySelective(article);
    }

    @Transactional(rollbackFor = Exception.class)
    public void modify(ArticleForm form) {
        int now = AppFunction.getTime();
        String articleId = form.getArticle_id();
        Article article = this.articleDao.selectByPrimaryKey(articleId);
        if (article == null) {
            throw new AppException("找不到相关文章！", 403);
        }
        if (StringUtils.isNotBlank(form.getCategory())) {
            article.setCategory(form.getCategory());
        }
        if (StringUtils.isNotBlank(form.getContent())) {
            article.setContent(form.getContent());
        }
        if (StringUtils.isNotBlank(form.getTitle())) {
            article.setTitle(form.getTitle());
        }
        List<String> list = form.getTag();
        if (list != null) {
            Tag tag = new Tag();
            tag.setArticle_id(articleId);
            this.tagDao.delete(tag);
            if (!list.isEmpty()) {
                for (String s : list) {
                    if (StringUtils.isNotBlank(s.trim())) {
                        Tag newTag = new Tag();
                        newTag.setArticle_id(articleId);
                        newTag.setTag(s);
                        newTag.setTag_id(FastuuidUtil.genUUID());
                        this.tagDao.insertSelective(newTag);
                    }
                }
            }
        }
        article.setStatus(form.getStatus());
        article.setUpdated_time(now);
        this.articleDao.updateByPrimaryKeySelective(article);
    }

    @DateTimeFields(fields = {"created_time", "updated_time"})
    public Map<String, Object> info(String articleId) {
        Article article = this.articleDao.selectByPrimaryKey(articleId);
        Map<String, Object> map = AppFunction.beanToMap(article);
        Tag tag = new Tag();
        tag.setArticle_id(articleId);
        List<String> tagList = new ArrayList<>();
        this.tagDao.select(tag).forEach(s -> {
            tagList.add(s.getTag());
        });
        map.put("tag", tagList);
        return map;
    }

    public List<Map<String, Object>> getAllUserArticle() {
        return this.articleDao.getList().stream()
                .collect(Collectors.groupingBy(
                        article -> (article.get("created_time_formatted") + "").substring(0, 4),
                        LinkedHashMap::new,
                        Collectors.toList()))
                .entrySet()
                .stream()
                .map(entry -> {
                    Map<String, Object> yearMap = new HashMap<>(8);
                    yearMap.put("year", entry.getKey());
                    yearMap.put("data", entry.getValue());
                    return yearMap;
                })
                .sorted((map1, map2) -> Integer.valueOf(map2.get("year") + "") - Integer.valueOf(map1.get("year") + ""))
                .collect(Collectors.toList());
    }
}
