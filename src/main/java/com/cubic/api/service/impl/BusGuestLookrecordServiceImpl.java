package com.cubic.api.service.impl;

import com.cubic.api.mapper.BusGuestLookrecordMapper;
import com.cubic.api.model.BusGuestLookrecord;
import com.cubic.api.service.BusGuestLookrecordService;
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
@SuppressWarnings("SpringJavaAutowiringInspection")
public class BusGuestLookrecordServiceImpl extends AbstractService<BusGuestLookrecord> implements BusGuestLookrecordService {
    @Resource
    private BusGuestLookrecordMapper busGuestLookrecordMapper;
	 /**
     * 查看带看记录
     * @param map
     * 
     * */
	@Override
	public List<BusGuestLookrecord> listBusGuestLookrecord(Map<String, Object> map) {
		
		return busGuestLookrecordMapper.listBusGuestLookrecord(map);
	}
	 /**
     * 创建带看记录
     * @param busGuestLookrecord
     * 
     * */
	@Override
	public void insertBusGuestLookrecord(BusGuestLookrecord busGuestLookrecord) {
		busGuestLookrecordMapper.insertBusGuestLookrecord(busGuestLookrecord);
		
	}

}
