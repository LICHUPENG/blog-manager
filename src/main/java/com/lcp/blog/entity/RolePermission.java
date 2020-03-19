package com.lcp.blog.entity;

import lombok.Data;

import javax.persistence.Id;

/**
 * @author lcp
 * @date 2020-01-07 14:31
 */
@Data
public class RolePermission {

    @Id
    private String permission_id;

    @Id
    private String role_id;
}
