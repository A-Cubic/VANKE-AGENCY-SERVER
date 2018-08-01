package com.cubic.api.service;

import java.util.List;
import java.util.Map;

import com.cubic.api.core.service.Service;
import com.cubic.api.model.Store;

/**
 * @author cubic
 * @date 2018/07/10
 */
public interface StoreService extends Service<Store> {

	
	/**
	 * 注册门店列表
	 * @param map
	 * */
	List<Store> ListBaseStore(Map<String, Object> map);
}
