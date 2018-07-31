package com.cubic.api.service;

import com.cubic.api.model.BusHouseClicklog;

import java.util.List;
import java.util.Map;

import com.cubic.api.core.service.Service;

/**
 * @author cubic
 * @date 2018/07/16
 */
public interface BusHouseClicklogService extends Service<BusHouseClicklog> {
	
	 /**
     * 添加房源点击次数日志
     * @param busHouseClicklog
     * 
     * */
	void  insertClickLog(BusHouseClicklog busHouseClicklog);
	 /**
     * 点击记录查询
     * @param map
     * 
     * */
	List<BusHouseClicklog> listClickLog(Map<String,Object> map);
}
