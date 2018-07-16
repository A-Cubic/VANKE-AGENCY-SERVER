package com.cubic.api.mapper;

import com.cubic.api.core.mapper.MyMapper;
import com.cubic.api.model.BusHouseClicklog;

public interface BusHouseClicklogMapper extends MyMapper<BusHouseClicklog> {
	
	 /**
     * 添加房源点击次数日志
     * @param busHouseClicklog
     * 
     * */
	void  insertClickLog(BusHouseClicklog busHouseClicklog);
}