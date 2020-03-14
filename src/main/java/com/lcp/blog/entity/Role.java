package com.lcp.blog.entity;

import lombok.Data;

import javax.persistence.Column;

/**
 * @author lcp
 * @date 2020-01-07 14:31
 */
@Data
public class Role {

    private static final long serialVersionUID = 4105947204329476602L;

    private String role_id;

    private String role;

    @Column(name = "`desc`")
    private String desc;
}
