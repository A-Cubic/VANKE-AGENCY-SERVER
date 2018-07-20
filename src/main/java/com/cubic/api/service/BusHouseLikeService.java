package com.cubic.api.service;

import com.cubic.api.model.BusHouseLike;
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
}
