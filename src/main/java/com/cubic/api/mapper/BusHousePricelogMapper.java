package com.cubic.api.mapper;

import java.util.List;
import java.util.Map;

import com.cubic.api.core.mapper.MyMapper;
import com.cubic.api.model.BusHousePricelog;

public interface BusHousePricelogMapper extends MyMapper<BusHousePricelog> {
	
	/**
	 * 添加修改价格日志
	 * @param BusHousePricelog
	 * */
	void insertPriceLog(BusHousePricelog busHousePricelog);
	
	/**
	 * 查看修改价格日志
	 * @param map
	 * */
	List<BusHousePricelog> listPriceLog(Map<String,Object> map);
}