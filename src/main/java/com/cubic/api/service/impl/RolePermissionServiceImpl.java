package com.cubic.api.service.impl;

import com.cubic.api.core.service.AbstractService;
import com.cubic.api.mapper.RolePermissionMapper;
import com.cubic.api.model.RolePermission;
import com.cubic.api.service.RolePermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author fei.yu
 * @date 2018/06/09
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RolePermissionServiceImpl extends AbstractService<RolePermission> implements RolePermissionService {
    @Resource
    private RolePermissionMapper rolePermissionMapper;

}
