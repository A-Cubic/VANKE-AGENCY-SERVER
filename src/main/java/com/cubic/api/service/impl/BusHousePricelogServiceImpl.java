package com.cubic.api.service.impl;

import com.cubic.api.mapper.BusHousePricelogMapper;
import com.cubic.api.model.BusHousePricelog;
import com.cubic.api.service.BusHousePricelogService;
import com.cubic.api.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * @author cubic
 * @date 2018/08/22
 */
@Service
@Transactional
@SuppressWarnings("SpringJavaAutowiringInspection")
public class BusHousePricelogServiceImpl extends AbstractService<BusHousePricelog> implements BusHousePricelogService {
    @Resource
    private BusHousePricelogMapper busHousePricelogMapper;

	@Override
	public void insertPriceLog(BusHousePricelog busHousePricelog) {
		busHousePricelogMapper.insertPriceLog(busHousePricelog);
		
	}

	@Override
	public List<BusHousePricelog> listPriceLog(Map<String, Object> map) {
		return busHousePricelogMapper.listPriceLog(map);
	}

}
