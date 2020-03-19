package com.lcp.blog.entity;

import lombok.Data;

import javax.persistence.Id;

@Data
public class UserRole {

    private static final long serialVersionUID = 5472647421003863966L;

    @Id
    private String user_id;

    private String role_id;
}
