package com.cubic.api.service;

import com.cubic.api.model.BusHouseTransactionRepair;

import java.util.List;
import java.util.Map;

import com.cubic.api.core.service.Service;

/**
 * @author cubic
 * @date 2018/08/02
 */
public interface BusHouseTransactionRepairService extends Service<BusHouseTransactionRepair> {
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
