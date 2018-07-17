package com.cubic.api.mapper;

import java.util.List;
import java.util.Map;

import com.cubic.api.core.mapper.MyMapper;
import com.cubic.api.model.BusHouseLookrecord;

public interface BusHouseLookrecordMapper extends MyMapper<BusHouseLookrecord> {
	
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
}