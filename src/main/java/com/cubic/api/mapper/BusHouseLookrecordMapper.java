package com.cubic.api.mapper;

import java.util.List;
import java.util.Map;

import com.cubic.api.core.mapper.MyMapper;
import com.cubic.api.model.BusHouseLookrecord;

public interface BusHouseLookrecordMapper extends MyMapper<BusHouseLookrecord> {
	
	 /**
     * 查看带看记录
     * @param map
     * 
     * */
	List<BusHouseLookrecord> listBusHouseLookrecord(Map<String,Object> map);
	
	 /**
     * 创建带看记录
     * @param busHouseLookrecord
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