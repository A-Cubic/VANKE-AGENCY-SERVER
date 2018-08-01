package com.cubic.api.mapper;

import java.util.List;
import java.util.Map;

import com.cubic.api.core.mapper.MyMapper;
import com.cubic.api.model.Store;

public interface StoreMapper extends MyMapper<Store> {
	/**
	 * 注册门店列表
	 * @param map
	 * */
	List<Store> ListBaseStore(Map<String, Object> map);

}