package org.wanke.com.service.impl;

import static java.util.Collections.emptyList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.wanke.com.entity.SysUser;
import org.wanke.com.mapper.SysMapper;

@Service
public class UserDetailsServiceImpl11 implements UserDetailsService {
	@Autowired
	SysMapper mapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUser user = mapper.findByUserAccount(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new org.springframework.security.core.userdetails.User(user.getAccount(), user.getPassword(),
				emptyList());
	}
}
