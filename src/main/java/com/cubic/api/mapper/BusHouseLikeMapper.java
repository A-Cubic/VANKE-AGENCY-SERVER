package com.cubic.api.mapper;

import com.cubic.api.core.mapper.MyMapper;
import com.cubic.api.model.BusHouseLike;

public interface BusHouseLikeMapper extends MyMapper<BusHouseLike> {
	/**
	 * 添加关注房源
	 * @param busHouseLike
	 * */
	void  insertHouseLike(BusHouseLike busHouseLike);
}