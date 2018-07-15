package com.cubic.api.mapper;

import java.util.List;
import java.util.Map;

import com.cubic.api.core.mapper.MyMapper;
import com.cubic.api.model.BusHouse;

public interface BusHouseMapper extends MyMapper<BusHouse> {
	
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
	
	 /**
     * 点击查看详细联系方式及房主姓名
     * @param map
     * 
     * */
	BusHouse DetailContacts(Map<String,Object> map);
	
	 /**
     * 点击查看房屋地址
     * @param map
     * 
     * */
	BusHouse DetailAddress(Map<String,Object> map);
	
	
	
}

