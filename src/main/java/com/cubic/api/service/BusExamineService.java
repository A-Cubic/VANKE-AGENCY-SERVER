package com.cubic.api.service;

import com.cubic.api.model.BusExamine;
import com.cubic.api.core.service.Service;

/**
 * @author cubic
 * @date 2018/07/18
 */
public interface BusExamineService extends Service<BusExamine> {
	 /**
     * 添加审核信息
     * @param busExamine
     * 
     * */
	void insertBusExamine(BusExamine busExamine);
}
