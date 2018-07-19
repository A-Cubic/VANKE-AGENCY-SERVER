package com.cubic.api.mapper;

import java.util.List;
import java.util.Map;

import com.cubic.api.core.mapper.MyMapper;
import com.cubic.api.model.BusExamine;

public interface BusExamineMapper extends MyMapper<BusExamine> {
	
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