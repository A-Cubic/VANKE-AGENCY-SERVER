package com.cubic.api.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cubic.api.core.mapper.MyMapper;
import com.cubic.api.model.User;
import com.cubic.api.model.home.CurrentUser;

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
    /**
     * 注册用户
     *
     * @param user
     * 
     */   
    void registerUser(User user);
   /**
    * 冻结账户或者解冻
    * 
    * */
    void updateSign(Map<String, Object> map);
    
    /**
     * 查询账户列表
     * */
    List<CurrentUser> findMyStoreUser(Map<String, Object> param);
    
    String getTokenFromDB(@Param("username") String username);
    void updateTokenDB(@Param("username") String username,@Param("token") String token);
    
}