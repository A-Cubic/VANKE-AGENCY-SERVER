package com.cubic.api.service;

import com.cubic.api.model.BusGuest;

import java.util.List;
import java.util.Map;

import com.cubic.api.core.service.Service;

/**
 * @author cubic
 * @date 2018/07/13
 */
public interface BusGuestService extends Service<BusGuest> {
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
