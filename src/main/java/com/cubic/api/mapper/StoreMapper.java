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
	/**
	 * 创建门店
	 * @param Store
	 * */
	void insertBaseStore(Store store);
	/**
	 * 添加门店范围
	 * @param map
	 * */
	void insertStoreRange(Map<String, Object> map);
	
	
	/**
	 * 通讯录门店分组
	 *
	 * */
	List<Store> storeUser();
	
	/**
	 * 判断是否存在
	 *
	 * */
	Store storeIF(Store store);

}