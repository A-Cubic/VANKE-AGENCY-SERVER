package com.cubic.api.service;

import com.cubic.api.model.BusHousePricelog;

import java.util.List;
import java.util.Map;

import com.cubic.api.core.service.Service;

/**
 * @author cubic
 * @date 2018/08/22
 */
public interface BusHousePricelogService extends Service<BusHousePricelog> {
	
	
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
