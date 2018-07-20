package com.cubic.api.service.impl;

import com.cubic.api.mapper.BusGuestRecordMapper;
import com.cubic.api.model.BusGuestRecord;
import com.cubic.api.service.BusGuestRecordService;
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
public class BusGuestRecordServiceImpl extends AbstractService<BusGuestRecord> implements BusGuestRecordService {
    @Resource
    private BusGuestRecordMapper busGuestRecordMapper;

	@Override
	public void insertGuestRecord(BusGuestRecord busGuestRecord) {
		busGuestRecordMapper.insertGuestRecord(busGuestRecord);
		
	}

	@Override
	public void updateGuestRecordIsTopOne(BusGuestRecord busGuestRecord) {
		busGuestRecordMapper.updateGuestRecordIsTopOne(busGuestRecord);
		
	}

	@Override
	public void updateGuestRecordIsTopZero(BusGuestRecord busGuestRecord) {
		busGuestRecordMapper.updateGuestRecordIsTopZero(busGuestRecord);
		
	}

	@Override
	public List<BusGuestRecord> listGuestRecord(Map<String, Object> map) {
		return busGuestRecordMapper.listGuestRecord(map);
	}

}
