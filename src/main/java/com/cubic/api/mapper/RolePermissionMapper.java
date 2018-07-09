package com.cubic.api.mapper;

import com.cubic.api.core.mapper.MyMapper;
import com.cubic.api.model.RolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author fei.yu
 * @date 2018/06/09
 */
public interface RolePermissionMapper extends MyMapper<RolePermission> {
    /**
     * 保存角色以及对应的权限ID
     *
     * @param roleId           角色ID
     * @param permissionIdList 权限ID列表
     */
    void saveRolePermission(@Param("roleId") Long roleId,
                            @Param("permissionIdList") List<Integer> permissionIdList);
}