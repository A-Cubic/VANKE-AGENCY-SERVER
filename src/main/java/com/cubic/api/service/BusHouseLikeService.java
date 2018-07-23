package com.cubic.api.service;

import com.cubic.api.model.BusHouseLike;

import java.util.List;
import java.util.Map;

import com.cubic.api.core.service.Service;

/**
 * @author cubic
 * @date 2018/07/20
 */
public interface BusHouseLikeService extends Service<BusHouseLike> {
	/**
	 * 添加关注房源
	 * @param busHouseLike
	 * */
	void  insertHouseLike(BusHouseLike busHouseLike);
	/**
	 * 查看房源关注
	 * @param map
	 * */
	List<BusHouseLike> listHouseLike(Map<String, Object> map);
	
	/**
	 * 取消关注
	 * @param busHouseLike
	 * */
	void deleteHouseLike(BusHouseLike busHouseLike);
}
