package com.cubic.api.service.impl;

import com.cubic.api.mapper.BusGuestMapper;
import com.cubic.api.model.BusGuest;
import com.cubic.api.service.BusGuestService;
import com.cubic.api.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * @author cubic
 * @date 2018/07/13
 */
@Service
@Transactional
@SuppressWarnings("SpringJavaAutowiringInspection")
public class BusGuestServiceImpl extends AbstractService<BusGuest> implements BusGuestService {
    @Resource
    private BusGuestMapper busGuestMapper;

	 /**
     * 创建客源信息
     * @param busGuest
     * 
     * */
 	@Override
   public void insertBusGuest(BusGuest busGuest){
 		
	   busGuestMapper.insertBusGuest(busGuest);
	   
   }
	 /**
     *更新跟进时间
     * @param busGuest
     * 
     * */
	@Override
	public void updateRecordTime(BusGuest busGuest) {
		busGuestMapper.updateRecordTime(busGuest);
		
	}
	 /**
     *转赠维护人
     * @param busGuest
     * 
     * */
	@Override
	public void updateRecordUser(BusGuest busGuest) {
		busGuestMapper.updateRecordUser(busGuest);
		
	}
	 /**
     *条件查询
     * @param map
     * 
     * */
	@Override
	public List<BusGuest> listBusGuest(Map<String, Object> map) {

		return busGuestMapper.listBusGuest(map);
	}
}
