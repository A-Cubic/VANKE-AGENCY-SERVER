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
@Transactional(rollbackFor = Exception.class)
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

	
	
	 /**
    * 点击查看详细联系方式及房主姓名
    * @param map
    * 
    * */
	@Override
	public BusHouse DetailContacts(Map<String,Object> map){
		
		return busHouseMapper.DetailContacts(map);
		
	}
	
	/**
    * 点击查看房屋地址
    * @param map
    * 
    * */
	@Override
	public BusHouse DetailAddress(Map<String,Object> map){
		return busHouseMapper.DetailAddress(map);
		
	}
	/**
	    * 更新房源跟进时间
	    * @param busHouse
	    * 
	    * */
	@Override
	public void updateRecordTime(BusHouse busHouse) {
		busHouseMapper.updateRecordTime(busHouse);		
	}
	/**
	    *转赠维护人
	    * @param busHouse
	    * 
	    * */
	@Override
	public void updateRecordUser(BusHouse busHouse) {
		busHouseMapper.updateRecordUser(busHouse);
		
	}
	/**
	    *改变钥匙持有人
	    * @param busHouse
	    * 
	    * */
	@Override
	public void updateKey(BusHouse busHouse) {
		busHouseMapper.updateKey(busHouse);
		
	}
}
