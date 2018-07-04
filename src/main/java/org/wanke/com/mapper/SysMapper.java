package org.wanke.com.mapper;

import java.util.List;
import org.wanke.com.entity.SysResources;
import org.wanke.com.entity.SysUser;

public interface SysMapper {
	 public List<SysResources> findAllSysResources();
	 public List<SysResources> findSysResourcesByUserId(long userId);
	 public SysUser findByUserAccount(String account);
}
