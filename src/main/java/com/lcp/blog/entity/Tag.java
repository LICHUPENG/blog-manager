package com.lcp.blog.entity;

import lombok.Data;

import javax.persistence.Id;

/**
 * @author lcp
 */
@Data
public class Tag {

    private String article_id;

    @Id
    private String tag_id;

    private String tag;
}
