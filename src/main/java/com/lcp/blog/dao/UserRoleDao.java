package com.lcp.blog.dao;

import com.lcp.blog.common.dao.BaseDao;
import com.lcp.blog.mapper.UserRoleMapper;
import com.lcp.blog.entity.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.Set;

/**
 * @author lcp
 * @date 2020-01-07 23:21
 */
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserRoleDao extends BaseDao<UserRole> {

    private final UserRoleMapper mapper;

    @Override
    protected Mapper<UserRole> getMapper() {
        return this.mapper;
    }

    public Set<String> getPermissionList(String userId) {
        return this.mapper.getPermissionList(userId);
    }

    public Set<String> getRoleList(String userId) {
        return this.mapper.getRoleList(userId);
    }
}
