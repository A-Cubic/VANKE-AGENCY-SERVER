package org.wanke.com.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.wanke.com.entity.SysResources;
import org.wanke.com.entity.SysUser;
import org.wanke.com.mapper.SysMapper;

/**
 * 自定义UserDetailsService 接口
 * 
 * @author fei.yu
 */
@Service

public class CustomUserService implements UserDetailsService { 

	@Autowired
	SysMapper mapper;

    public UserDetails loadUserByUsername(String username) {
        SysUser user = mapper.findByUserAccount(username);
        if (user != null) {
            List<SysResources> resources = mapper.findSysResourcesByUserId(user.getId());
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (SysResources resource : resources) {
                if (resource != null && resource.getName() != null) {
                    GrantedAuthority grantedAuthority = new MyGrantedAuthority(resource.getRes_url(), resource.getMethod());
                    grantedAuthorities.add(grantedAuthority);
                }
            }
            return new User(user.getAccount(), user.getPassword(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("admin: " + username + " do not exist!");
        }
    }

}
