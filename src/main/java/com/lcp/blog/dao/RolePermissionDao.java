package com.lcp.blog.dao;

import com.lcp.blog.common.dao.BaseDao;
import com.lcp.blog.dao.mapper.RolePermissionMapper;
import com.lcp.blog.entity.RolePermission;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author lcp
 * @date 2020-01-13 00:30
 */
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class RolePermissionDao extends BaseDao<RolePermission> {

    private final RolePermissionMapper mapper;

    @Override
    protected Mapper<RolePermission> getMapper() {
        return this.mapper;
    }
}
