package com.lcp.blog.entity;

import lombok.Data;

/**
 * @author lcp
 * @date 2020-01-07 14:31
 */
@Data
public class Permission {

    private String permission_id;

    private String name;

    private String permission;

    private Integer status;
}
