package com.lcp.blog.dao.mapper;

import com.lcp.blog.entity.UserRole;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.Set;

public interface UserRoleMapper extends Mapper<UserRole> {

    /**
     * 获取所有权限
     * @param userId 用户ID
     * @return 用户的所有权限
     */
    Set<String> getPermissionList(@Param("user_id") String userId);

    /**
     * 获取所有角色
     * @param userId 用户ID
     * @return 用户的所有角色
     */
    Set<String> getRoleList(@Param("uer_id") String userId);
}
