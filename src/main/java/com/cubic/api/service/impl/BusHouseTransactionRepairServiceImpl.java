package com.cubic.api.service.impl;

import com.cubic.api.mapper.BusHouseTransactionRepairMapper;
import com.cubic.api.model.BusHouseTransactionRepair;
import com.cubic.api.service.BusHouseTransactionRepairService;
import com.cubic.api.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * @author cubic
 * @date 2018/08/02
 */
@Service
@Transactional
@SuppressWarnings("SpringJavaAutowiringInspection")
public class BusHouseTransactionRepairServiceImpl extends AbstractService<BusHouseTransactionRepair> implements BusHouseTransactionRepairService {
    @Resource
    private BusHouseTransactionRepairMapper busHouseTransactionRepairMapper;

	@Override
	public List<BusHouseTransactionRepair> ListTransactionRepair(Map<String, Object> map) {

		return busHouseTransactionRepairMapper.ListTransactionRepair(map);
	}

	@Override
	public void insertTransactionRepair(BusHouseTransactionRepair bean) {
		busHouseTransactionRepairMapper.insertTransactionRepair(bean);
		
	}

}
