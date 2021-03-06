package com.cubic.api.service;

import com.cubic.api.core.service.Service;
import com.cubic.api.model.User;
import com.cubic.api.model.home.CurrentUser;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Map;

/**
 * @author fei.yu
 * @date 2018/06/09
 */
public interface UserService extends Service<User> {
    /**
     * 获取所有用户以及对应角色
     *
     * @return 用户列表
     */
    List<User> findAllUserWithRole();

    /**
     * 按条件查询用户信息
     *
     * @param column 列名
     * @param param  参数map
     * @return 用户
     */
    User findDetailBy(String column, Object param);


    /**
     * 按用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户
     * @throws UsernameNotFoundException 用户名找不到
     */
    User findDetailByUsername(String username) throws UsernameNotFoundException;

    /**
     * 按用户名更新最后一次登录时间
     *
     * @param username 用户名
     */
    void updateLoginTimeByUsername(String username);

    /**
     * 验证用户密码
     *
     * @param rawPassword     原密码
     * @param encodedPassword 加密后的密码
     * @return boolean
     */
    boolean verifyPassword(String rawPassword, String encodedPassword);
    
    
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
    String registerUser(User user);
    
    /**
     * 冻结账户或者解冻
     * 
     * */
     void updateSign(Map<String, Object> map);
    
    /**
     * 查询账户列表
     * */
    List<CurrentUser> findMyStoreUser(Map<String, Object> param);
    
    String getTokenFromDB(String username);
    void updateTokenDB(String username,String token);
    
    /**
     * 查询切换账号门店
     * */
    List<CurrentUser> UserCut(Map<String, Object> map);
}
