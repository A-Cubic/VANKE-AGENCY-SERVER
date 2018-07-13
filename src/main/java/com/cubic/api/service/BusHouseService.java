package com.cubic.api.service;

import com.cubic.api.model.BusHouse;

import java.util.List;
import java.util.Map;

import com.cubic.api.core.service.Service;

/**
 * @author cubic
 * @date 2018/07/12
 */
public interface BusHouseService extends Service<BusHouse> {

	
	 /**
     * 按条件查询列表
     * @param map
     * 
     * */
	List<BusHouse> ListBusHouse(Map<String,Object> map);
	
	 /**
     * 创建房源
     * @param busHouse
     * 
     * */
	void insertBusHouse(BusHouse busHouse);
}
