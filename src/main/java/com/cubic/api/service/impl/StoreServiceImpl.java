package com.cubic.api.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cubic.api.core.service.AbstractService;
import com.cubic.api.mapper.StoreMapper;
import com.cubic.api.model.Store;
import com.cubic.api.service.StoreService;

/**
 * @author cubic
 * @date 2018/07/10
 */
@Service
@Transactional
public class StoreServiceImpl extends AbstractService<Store> implements StoreService {
    @Resource
    private StoreMapper storeMapper;

	@Override
	public List<Store> ListBaseStore(Map<String, Object> map) {
		return storeMapper.ListBaseStore(map);
	}
	/**
	 * 创建门店
	 * 
	 * */
	@Override
	public void insertBaseStore(Store store) {
		storeMapper.insertBaseStore(store);
		
	}
/**
 * 添加门店范围
 * 
 * */
	@Override
	public void insertStoreRange(Map<String, Object> map) {
		storeMapper.insertStoreRange(map);
		
	}
	/**
	 * 通讯录
	 * */
	@Override
	public List<Store> storeUser() {
		// TODO Auto-generated method stub
		return storeMapper.storeUser();
	}
    
    
 

}
