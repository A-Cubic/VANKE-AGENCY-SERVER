package com.cubic.api.service;

import com.cubic.api.model.BusHouseTransaction;

import java.util.List;
import java.util.Map;

import com.cubic.api.core.service.Service;

/**
 * @author cubic
 * @date 2018/07/25
 */
public interface BusHouseTransactionService extends Service<BusHouseTransaction> {

	
	
	/**
	 * 条件查询成交记录
	 * @param map
	 * */
   List<BusHouseTransaction>  listTransaction(Map<String,Object> map);
   
	/**
	 * 查询单个数据
	 * @param id
	 * */
  BusHouseTransaction detailTransaction(Map<String,Object> map);
  
	/**
	 * 添加成交记录
	 * @param id
	 * */
  void insertTransaction(BusHouseTransaction busHouseTransaction);
}
