package com.cubic.api.mapper;

import java.util.List;
import java.util.Map;

import com.cubic.api.core.mapper.MyMapper;
import com.cubic.api.model.BusGuest;

public interface BusGuestMapper extends MyMapper<BusGuest> {
	
	 /**
     * 创建客源信息
     * @param busGuest
     * 
     * */
	void insertBusGuest(BusGuest busGuest);
	 /**
     * 更新客源的跟进时间
     * @param busGuest
     * 
     * */
	void updateRecordTime(BusGuest busGuest);
	
	 /**
     * 转赠维护人
     * @param busGuest
     * 
     * */
	void updateRecordUser(BusGuest busGuest);
	 /**
     * 条件查询
     * @param map
     * 
     * */
	List<BusGuest> listBusGuest(Map<String,Object> map);
}