package com.cubic.api.mapper;

import com.cubic.api.core.mapper.MyMapper;
import com.cubic.api.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author fei.yu
 * @date 2018/06/09
 */
public interface UserMapper extends MyMapper<User> {
    /**
     * 获取所有用户以及对应角色
     *
     * @return 用户列表
     */
    List<User> findAllUserWithRole();

    /**
     * 按条件查询用户信息
     *
     * @param param 参数map
     * @return 用户
     */
    User findDetailBy(Map<String, Object> param);

    /**
     * 按用户名更新最后登陆时间
     *
     * @param username 用户名
     */
    void updateLoginTimeByUsername(@Param("username") String username);
    
    /**
     * 按照真实姓名查询列表
     *
     * @param param 参数map
     * @return 用户
     */    
    List<User> listUserInfo(Map<String, Object> param);
}