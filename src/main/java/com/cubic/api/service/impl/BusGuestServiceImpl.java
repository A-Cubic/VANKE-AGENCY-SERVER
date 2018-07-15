package com.cubic.api.service.impl;

import com.cubic.api.mapper.BusGuestMapper;
import com.cubic.api.model.BusGuest;
import com.cubic.api.service.BusGuestService;
import com.cubic.api.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
 		
	   return busGuestMapper.insertBusGuest(busGuest);
	   
   }
}
