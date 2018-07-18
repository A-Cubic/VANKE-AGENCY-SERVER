package com.cubic.api.service;

import com.cubic.api.model.BusGuestLookrecord;

import java.util.List;
import java.util.Map;

import com.cubic.api.core.service.Service;

/**
 * @author cubic
 * @date 2018/07/17
 */
public interface BusGuestLookrecordService extends Service<BusGuestLookrecord> {
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
