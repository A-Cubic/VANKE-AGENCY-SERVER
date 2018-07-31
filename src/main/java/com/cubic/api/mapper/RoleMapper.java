package com.cubic.api.mapper;

import com.cubic.api.core.mapper.MyMapper;
import com.cubic.api.model.Resource;
import com.cubic.api.model.Role;

import java.util.List;
import java.util.Map;

/**
 * @author fei.yu
 * @date 2018/06/09
 */
public interface RoleMapper extends MyMapper<Role> {
    /**
     * 获取所有角色以及对应的权限
     *
     * @return 角色可控资源列表
     */
    List<Resource> findAllRoleWithPermission();
    /**
     * 按权限获得角色信息
     *
     * @return 角色可控资源列表
     */
    List<Role>  listDesc(Map<String, Object> param);
}