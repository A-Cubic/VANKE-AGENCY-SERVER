package com.cubic.api.service.impl;

import com.cubic.api.mapper.BusHouseRecordMapper;
import com.cubic.api.model.BusHouseRecord;
import com.cubic.api.service.BusHouseRecordService;
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
public class BusHouseRecordServiceImpl extends AbstractService<BusHouseRecord> implements BusHouseRecordService {
    @Resource
    private BusHouseRecordMapper busHouseRecordMapper;
    /**
     * 添加跟进信息
     * @param busHouseRecord
     * 
     * */
	@Override
	public void insertHouseRecord(BusHouseRecord busHouseRecord) {
		busHouseRecordMapper.insertHouseRecord(busHouseRecord);
		
	}
	   /**
	    * 设置跟进信息为置顶
	    * @param busHouseRecord
	    * 
	    * */
	@Override
	public void updateHouseRecordIsTopOne(BusHouseRecord busHouseRecord) {
		busHouseRecordMapper.updateHouseRecordIsTopOne(busHouseRecord);
		
	}
	  /**
	    * 取消跟进信息置顶
	    * @param busHouseRecord
	    * 
	    * */
	@Override
	public void updateHouseRecordIsTopZero(BusHouseRecord busHouseRecord) {
		busHouseRecordMapper.updateHouseRecordIsTopZero(busHouseRecord);
		
	}
	   
	   /**
	    * 查询跟进信息
	    * @param map
	    * 
	    * */
	@Override
	public List<BusHouseRecord> listHouseRecord(Map<String, Object> map) {

		return busHouseRecordMapper.listHouseRecord(map);
	}

}
