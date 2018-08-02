package com.cubic.api.mapper;

import java.util.List;
import java.util.Map;

import com.cubic.api.core.mapper.MyMapper;
import com.cubic.api.model.BusHouseTransactionRepair;

public interface BusHouseTransactionRepairMapper extends MyMapper<BusHouseTransactionRepair> {
	
	/**
	 * 查询成交的补交记录
	 * @param map
	 * */
    List<BusHouseTransactionRepair> ListTransactionRepair(Map<String,Object> map);
    
	/**
	 * 补交成交欠款
	 * @param BusHouseTransactionRepair
	 * */
    void insertTransactionRepair(BusHouseTransactionRepair bean);
}