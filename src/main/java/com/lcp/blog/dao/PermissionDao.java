package com.lcp.blog.dao;

import com.lcp.blog.common.dao.BaseDao;
import com.lcp.blog.mapper.PermissionMapper;
import com.lcp.blog.entity.Permission;
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
public class PermissionDao extends BaseDao<Permission> {

    private final PermissionMapper mapper;

    /**
     * 启用
     */
    public static final int ON = 1;

    /**
     * 关闭
     */
    public static final int OFF = 1;

    @Override
    protected Mapper<Permission> getMapper() {
        return this.mapper;
    }
}
