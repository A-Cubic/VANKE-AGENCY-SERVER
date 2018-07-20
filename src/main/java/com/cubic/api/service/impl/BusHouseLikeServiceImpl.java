package com.cubic.api.service.impl;

import com.cubic.api.mapper.BusHouseLikeMapper;
import com.cubic.api.model.BusHouseLike;
import com.cubic.api.service.BusHouseLikeService;
import com.cubic.api.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author cubic
 * @date 2018/07/20
 */
@Service
@Transactional(rollbackFor = Exception.class)
@SuppressWarnings("SpringJavaAutowiringInspection")
public class BusHouseLikeServiceImpl extends AbstractService<BusHouseLike> implements BusHouseLikeService {
    @Resource
    private BusHouseLikeMapper busHouseLikeMapper;
	/***
	 * 添加关注
	 * @param busHouseLike
	 * 
	 * */
	@Override
	public void insertHouseLike(BusHouseLike busHouseLike) {
		busHouseLikeMapper.insertHouseLike(busHouseLike);
		
	}

}
