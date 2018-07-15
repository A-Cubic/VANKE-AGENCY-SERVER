package com.cubic.api.service;

import com.cubic.api.model.BusGuest;
import com.cubic.api.core.service.Service;

/**
 * @author cubic
 * @date 2018/07/13
 */
public interface BusGuestService extends Service<BusGuest> {
	 /**
     * 创建客源信息
     * @param busGuest
     * 
     * */
	void insertBusGuest(BusGuest busGuest);
}