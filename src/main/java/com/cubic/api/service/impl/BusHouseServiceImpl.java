package com.cubic.api.service.impl;

import com.cubic.api.mapper.BusHouseMapper;
import com.cubic.api.model.BusHouse;
import com.cubic.api.service.BusHouseService;
import com.cubic.api.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * @author cubic
 * @date 2018/07/12
 */
@Service
@Transactional
@SuppressWarnings("SpringJavaAutowiringInspection")
public class BusHouseServiceImpl extends AbstractService<BusHouse> implements BusHouseService {
    @Resource
    private BusHouseMapper busHouseMapper;

    
    /**
     * 按条件查询列表
     * @param map
     * 
     * */
	@Override
	public List<BusHouse> ListBusHouse(Map<String,Object> map) {
		return busHouseMapper.ListBusHouse(map);
	}

    /**
     * 创建房源
     * @param busHouse
     * 
     * */
	@Override
	public void insertBusHouse(BusHouse busHouse) {	
		busHouseMapper.insertBusHouse(busHouse);	
	}

}
