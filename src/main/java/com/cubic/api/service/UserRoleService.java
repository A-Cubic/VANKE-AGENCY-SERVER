package com.cubic.api.service;

import com.cubic.api.core.service.Service;
import com.cubic.api.model.User;
import com.cubic.api.model.UserRole;

/**
 * @author fei.yu
 * @date 2018/06/09
 */
public interface UserRoleService extends Service<UserRole> {
    /**
     * 更新用户角色
     *
     * @param user 用户
     */
    void updateUserRole(User user);
}
