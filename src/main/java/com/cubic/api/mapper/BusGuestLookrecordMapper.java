package com.cubic.api.mapper;

import java.util.List;
import java.util.Map;

import com.cubic.api.core.mapper.MyMapper;
import com.cubic.api.model.BusGuestLookrecord;

public interface BusGuestLookrecordMapper extends MyMapper<BusGuestLookrecord> {
	
	 /**
     * 查看带看记录
     * @param map
     * 
     * */
	List<BusGuestLookrecord> listBusGuestLookrecord(Map<String,Object> map);
	
	 /**
     * 创建带看记录
     * @param busGuestLookrecord
     * 
     * */
	void insertBusGuestLookrecord(BusGuestLookrecord busGuestLookrecord);
}