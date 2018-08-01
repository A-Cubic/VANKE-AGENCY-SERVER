package com.cubic.api.service.impl;

import com.cubic.api.mapper.BusHouseTransactionMapper;
import com.cubic.api.model.BusHouseTransaction;
import com.cubic.api.service.BusHouseTransactionService;
import com.cubic.api.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * @author cubic
 * @date 2018/07/25
 */
@Service
@Transactional
@SuppressWarnings("SpringJavaAutowiringInspection")
public class BusHouseTransactionServiceImpl extends AbstractService<BusHouseTransaction> implements BusHouseTransactionService {
    @Resource
    private BusHouseTransactionMapper busHouseTransactionMapper;

    /**
     * 按条件查询成交记录
     * @param map
     * */
	@Override
	public List<BusHouseTransaction> listTransaction(Map<String, Object> map) {

		return busHouseTransactionMapper.listTransaction(map);
	}

	@Override
	public BusHouseTransaction detailTransaction(Map<String,Object> map) {

		return busHouseTransactionMapper.detailTransaction(map);
	}
	/**
	 * 添加成交
	 * @param busHouseTransaction
	 * */
	@Override
	public void insertTransaction(BusHouseTransaction busHouseTransaction) {
		busHouseTransactionMapper.insertTransaction(busHouseTransaction);
		
	}

}
