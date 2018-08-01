package com.cubic.api.mapper;

import java.util.List;
import java.util.Map;

import com.cubic.api.core.mapper.MyMapper;
import com.cubic.api.model.BusHouseTransaction;

public interface BusHouseTransactionMapper extends MyMapper<BusHouseTransaction> {
	
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