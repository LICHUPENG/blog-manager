package com.lcp.blog.dao;

import com.lcp.blog.common.dao.BaseDao;
import com.lcp.blog.mapper.RoleMapper;
import com.lcp.blog.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author lcp
 * @date 2020-01-07 14:31
 */
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class RoleDao extends BaseDao<Role> {

    private final RoleMapper roleMapper;

    @Override
    protected Mapper<Role> getMapper() {
        return this.roleMapper;
    }
}
