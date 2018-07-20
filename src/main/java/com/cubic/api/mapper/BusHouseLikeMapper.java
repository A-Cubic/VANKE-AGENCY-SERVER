package com.cubic.api.mapper;

import java.util.List;
import java.util.Map;

import com.cubic.api.core.mapper.MyMapper;
import com.cubic.api.model.BusHouseLike;

public interface BusHouseLikeMapper extends MyMapper<BusHouseLike> {
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
}