package com.lcp.blog.dao;

import cn.lcp.admin.entity.BlogUser;
import cn.lcp.common.dao.BaseDao;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author lcp
 * @date 201-12-05 14:58
 */
@Service
public class BlogUserDao extends BaseDao<BlogUser> {
    @Override
    protected Mapper<BlogUser> getMapper() {
        return null;
    }
}
