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
@Transactional(rollbackFor = Exception.class)
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
	 /**
     * 时间任务的查看所有上次维护时间距离现在时间大于10天或15天的客源
     * 
     * 
     * */
	@Override
	public List<BusGuest> listRecordTime() {

		return busGuestMapper.listRecordTime();
	}
	/**
	 * 按照搜索条件查询成交客源
	 * @param map
	 * */
	@Override
	public List<BusGuest> listTransactionGuest(Map<String, Object> map) {

		return busGuestMapper.listTransactionGuest(map);
	}
	/**
	 * 掉到共享池里的客源修改状态
	 * */
	@Override
	public void updateIsShareNull(BusGuest busGuest) {
		busGuestMapper.updateIsShareNull(busGuest);
		
	}
	/**
	 * 分配客源
	 * @param map
	 * */
	@Override
	public void updateAllocation(Map<String, Object> map) {
		busGuestMapper.updateAllocation(map);
		
	}
	@Override
	public void updateGuestIsKeyDown(BusGuest busGuest) {
		busGuestMapper.updateGuestIsKeyDown(busGuest);
		
	}
	@Override
	public void updateGuestIsKeyUp(Map<String, Object> map) {
		busGuestMapper.updateGuestIsKeyUp(map);
		
	}
}
