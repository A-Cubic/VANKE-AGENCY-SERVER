package com.cubic.api.service;

import com.cubic.api.model.BusExamine;

import java.util.List;
import java.util.Map;

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
	
	 /**
     * 处理审批
     * @param busExamine
     * 
     * */
	void updateResult(BusExamine busExamine);
	 /**
     * 开始审核(状态设置为审核中:1)
     * @param busExamine
     * 
     * */
	void updateState(BusExamine busExamine);
	 /**
     * 条件查询
     * @param map
     * 
     * */
	List<BusExamine>  listBusExamine(Map<String,Object> map);
}
