package org.wanke.com.service.impl;

import org.springframework.security.core.GrantedAuthority;

/**
 * 权限类型，负责存储权限和角色
 *
 * @author fei.yu
 */
public class GrantedAuthorityImpl implements GrantedAuthority {
	private static final long serialVersionUID = -1939685575950481672L;
	private String authority;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
