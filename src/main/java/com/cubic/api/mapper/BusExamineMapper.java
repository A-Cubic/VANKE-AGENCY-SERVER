package com.cubic.api.mapper;

import com.cubic.api.core.mapper.MyMapper;
import com.cubic.api.model.BusExamine;

public interface BusExamineMapper extends MyMapper<BusExamine> {
	
	 /**
     * 添加审核信息
     * @param busExamine
     * 
     * */
	void insertBusExamine(BusExamine busExamine);
}