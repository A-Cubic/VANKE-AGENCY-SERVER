package com.cubic.api.service.impl;

import com.cubic.api.mapper.BusHouseClicklogMapper;
import com.cubic.api.model.BusHouseClicklog;
import com.cubic.api.service.BusHouseClicklogService;
import com.cubic.api.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * @author cubic
 * @date 2018/07/16
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BusHouseClicklogServiceImpl extends AbstractService<BusHouseClicklog> implements BusHouseClicklogService {
    @Resource
    private BusHouseClicklogMapper busHouseClicklogMapper;
    /**
     * 添加房源点击次数日志
     * @param busHouseClicklog
     * 
     * */
	@Override
	public void insertClickLog(BusHouseClicklog busHouseClicklog) {
		
		busHouseClicklogMapper.insertClickLog(busHouseClicklog);
	}
    /**
     * 查看点击记录
     * @param map
     * 
     * */
	@Override
	public List<BusHouseClicklog> listClickLog(Map<String, Object> map) {

		return busHouseClicklogMapper.listClickLog(map);
	}


}
