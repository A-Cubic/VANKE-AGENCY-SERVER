package com.cubic.api.service.impl;

import com.cubic.api.mapper.BusExamineMapper;
import com.cubic.api.model.BusExamine;
import com.cubic.api.service.BusExamineService;
import com.cubic.api.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * @author cubic
 * @date 2018/07/18
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BusExamineServiceImpl extends AbstractService<BusExamine> implements BusExamineService {
    @Resource
    private BusExamineMapper busExamineMapper;
    /**
     * 添加审核信息
     * @param busExamine
     * 
     * */
	
	@Override
	public void insertBusExamine(BusExamine busExamine) {
		busExamineMapper.insertBusExamine(busExamine);
		
	}
	 /**
     * 处理审核
     * @param busExamine
     * 
     * */
	@Override
	public void updateResult(BusExamine busExamine) {
		busExamineMapper.updateResult(busExamine);
		
	}
	 /**
     * 条件查询
     * @param map
     * 
     * */
	@Override
	public List<BusExamine> listBusExamine(Map<String, Object> map) {

		return busExamineMapper.listBusExamine(map);
	}
	 /**
     * 开始审核
     * @param busExamine
     * 
     * */
	@Override
	public void updateState(BusExamine busExamine) {
		busExamineMapper.updateState(busExamine);
		
	}

}
