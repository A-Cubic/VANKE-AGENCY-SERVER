package com.cubic.api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cubic.api.core.service.AbstractService;
import com.cubic.api.mapper.PermissionMapper;
import com.cubic.api.model.Permission;
import com.cubic.api.service.PermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author fei.yu
 * @date 2018/06/09
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PermissionServiceImpl extends AbstractService<Permission> implements PermissionService {
    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public List<JSONObject> findAllResourcePermission() {
        return this.permissionMapper.findAllResourcePermission();
    }
}
