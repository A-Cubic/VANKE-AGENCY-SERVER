package com.cubic.api.service.impl;

import com.cubic.api.core.service.AbstractService;
import com.cubic.api.mapper.UserRoleMapper;
import com.cubic.api.model.User;
import com.cubic.api.model.UserRole;
import com.cubic.api.service.UserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;

/**
 * @author fei.yu
 * @date 2018/06/09
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserRoleServiceImpl extends AbstractService<UserRole> implements UserRoleService {
    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public void updateUserRole(final User user) {
        final Condition condition = new Condition(UserRole.class);
        condition.createCriteria().andCondition("user_id = ", user.getId());
        final UserRole userRole = new UserRole()
                .setUserId(user.getId())
                .setRoleId(user.getRoleId());
        this.userRoleMapper.updateByConditionSelective(userRole, condition);
    }
}
