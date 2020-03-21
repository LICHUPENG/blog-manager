package com.lcp.blog.dao;

import com.lcp.blog.common.dao.BaseDao;
import com.lcp.blog.entity.BlogUser;
import com.lcp.blog.mapper.BlogUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author lcp
 * @date 201-12-05 14:58
 */
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class BlogUserDao extends BaseDao<BlogUser> {

    private final BlogUserMapper mapper;

    @Override
    protected Mapper<BlogUser> getMapper() {
        return this.mapper;
    }
}
