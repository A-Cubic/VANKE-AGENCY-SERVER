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
	
	/**
	 * 查询门店详情信息(带门店范围)
	 *
	 * */
	 List<Store> listStoreInFo(Map<String, Object> map);
	/**
	 * 修改门店信息时先删除门店范围
	 *
	 * */
	void  deleteStoreRange(String id);
	/**
	 * 修改门店信息
	 *
	 * */
	void updateStore(Store store);

}