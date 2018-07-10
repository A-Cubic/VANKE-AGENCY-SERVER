package com.cubic.api.mapper;

import com.alibaba.fastjson.JSONObject;
import com.cubic.api.core.mapper.MyMapper;
import com.cubic.api.model.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author fei.yu
 * @date 2018/06/09
 */
public interface PermissionMapper extends MyMapper<Permission> {
    /**
     * 找到所有权限可控资源
     *
     * @return Json对象列表
     */
    List<JSONObject> findAllResourcePermission();

    /**
     * 获取所有权限代码
     *
     * @return 代码列表
     */
    @Select("SELECT p.code FROM `sys_permission` p")
    List<String> findAllCode();
}