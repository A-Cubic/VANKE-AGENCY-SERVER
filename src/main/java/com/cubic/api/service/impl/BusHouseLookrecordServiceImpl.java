package com.cubic.api.service.impl;

import com.cubic.api.mapper.BusHouseLookrecordMapper;
import com.cubic.api.model.BusHouseLookrecord;
import com.cubic.api.service.BusHouseLookrecordService;
import com.cubic.api.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * @author cubic
 * @date 2018/07/17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BusHouseLookrecordServiceImpl extends AbstractService<BusHouseLookrecord> implements BusHouseLookrecordService {
    @Resource
    private BusHouseLookrecordMapper busHouseLookrecordMapper;
	 /**
     * 查看带看记录
     * @param busHouse
     * 
     * */
	@Override
	public List<BusHouseLookrecord> listBusHouseLookrecord(Map<String, Object> map) {

		return busHouseLookrecordMapper.listBusHouseLookrecord(map);
	}
	
	 /**
    * 创建带看记录
    * @param busHouse
    * 
    * */
	@Override
	public void insertBusHouseLookrecord(BusHouseLookrecord busHouseLookrecord) {
		busHouseLookrecordMapper.insertBusHouseLookrecord(busHouseLookrecord);
		
	}

}
