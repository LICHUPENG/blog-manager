package com.lcp.blog.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lcp
 * @date 2019-12-05 14:33
 */
@Data
public class BlogUser implements Serializable {

    private static final long serialVersionUID = 993535705446927059L;

    private String user_id;

    private String nickname;

    private String password;

    private String mobile;

    private String email;

    private String avatar;

    private String salt;

    private Integer created_time;

    private String user_info_id;

    private String site_id;
}
