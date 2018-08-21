package com.cubic.api.service;

import com.cubic.api.model.BusHouseLookrecord;

import java.util.List;
import java.util.Map;

import com.cubic.api.core.service.Service;

/**
 * @author cubic
 * @date 2018/07/17
 */
public interface BusHouseLookrecordService extends Service<BusHouseLookrecord> {

	
	 /**
     * 查看带看记录
     * @param busHouse
     * 
     * */
	List<BusHouseLookrecord> listBusHouseLookrecord(Map<String,Object> map);
	
	 /**
     * 创建带看记录
     * @param busHouse
     * 
     * */
	void insertBusHouseLookrecord(BusHouseLookrecord busHouseLookrecord);
	
	 /**
     * 删除
     * @param houseId
     * 
     * */
	void deleteLookrecord(String houseId);
}
