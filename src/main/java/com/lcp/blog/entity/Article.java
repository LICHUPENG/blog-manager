package com.lcp.blog.entity;

import lombok.Data;

import javax.persistence.Id;

/**
 * @author dell
 */
@Data
public class Article {

    @Id
    private String article_id;

    private String title;

    private String category;

    private String content;

    private Integer status;

    private Integer created_time;

    private Integer updated_time;
}
