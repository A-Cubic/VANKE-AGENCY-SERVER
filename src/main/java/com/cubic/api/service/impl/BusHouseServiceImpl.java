package com.cubic.api.service.impl;

import com.cubic.api.mapper.BusHouseMapper;
import com.cubic.api.model.BusHouse;
import com.cubic.api.model.home.CurrentUser;
import com.cubic.api.service.BusHouseService;
import com.cubic.api.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * @author cubic
 * @date 2018/07/12
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BusHouseServiceImpl extends AbstractService<BusHouse> implements BusHouseService {
    @Resource
    private BusHouseMapper busHouseMapper;

    
    /**
     * 按条件查询列表
     * @param map
     * 
     * */
	@Override
	public List<BusHouse> ListBusHouse(Map<String,Object> map) {
		return busHouseMapper.ListBusHouse(map);
	}

    /**
     * 创建房源
     * @param busHouse
     * 
     * */
	@Override
	public void insertBusHouse(BusHouse busHouse) {	
		busHouseMapper.insertBusHouse(busHouse);	
	}

	
	
	 /**
    * 点击查看详细联系方式及房主姓名
    * @param map
    * 
    * */
	@Override
	public BusHouse DetailContacts(Map<String,Object> map){
		
		return busHouseMapper.DetailContacts(map);
		
	}
	
	/**
    * 点击查看房屋地址
    * @param map
    * 
    * */
	@Override
	public BusHouse DetailAddress(Map<String,Object> map){
		return busHouseMapper.DetailAddress(map);
		
	}
	/**
	    * 更新房源跟进时间
	    * @param busHouse
	    * 
	    * */
	@Override
	public void updateRecordTime(BusHouse busHouse) {
		busHouseMapper.updateRecordTime(busHouse);		
	}
	/**
	    *转赠维护人
	    * @param busHouse
	    * 
	    * */
	@Override
	public void updateRecordUser(BusHouse busHouse) {
		busHouseMapper.updateRecordUser(busHouse);
		
	}
	/**
	    *改变钥匙持有人
	    * @param busHouse
	    * 
	    * */
	@Override
	public void updateKey(BusHouse busHouse) {
		busHouseMapper.updateKey(busHouse);
		
	}
	 /**
     * 时间任务的查看所有上次维护时间距离现在时间大于10天或15天的房源
     * @param map
     * 
     * */
	@Override
	public List<BusHouse> listRecordTime() {

		return busHouseMapper.listRecordTime();
	}
	 /**
     * 查询我关注的房源
     * @param map
     * 
     * */
	@Override
	public List<BusHouse> listMyLikeHouse(Map<String, Object> map) {

		return busHouseMapper.listMyLikeHouse(map);
	}
	 /**
     * 查询优质房源
     * @param map
     * 
     * */
	@Override
	public List<BusHouse> listIsfine(Map<String, String> map) {

		return busHouseMapper.listIsfine(map);
	}
	 /**
     * 查询详细房源信息
     * @param map
     * 
     * */
	@Override
	public BusHouse detailHouse(Map<String, String> map) {
		return busHouseMapper.detailHouse(map);
	}
	
	/**
     * 修改维护人（共享池）
     * @param busHouse
     * 
     * */
	@Override
	public void updateShareState(BusHouse busHouse) {
		busHouseMapper.updateShareState(busHouse);
		
	}
	/**
	 * 掉到共享池里清空维护人
	 * */
	@Override
	public void updateIsShareNull(BusHouse busHouse) {
		busHouseMapper.updateIsShareNull(busHouse);
		
	}
	/**
	 * 验证地址是否存在
	 * @param map
	 * */
	@Override
	public List<BusHouse> findIsAddress(BusHouse busHouse) {

		return busHouseMapper.findIsAddress(busHouse);
	}
	/**
	 * 取消钥匙人
	 * @param busHouse
	 * */
	@Override
	public void updateCancelKey(BusHouse busHouse) {
		busHouseMapper.updateCancelKey(busHouse);
		
	}
	/**
	 * 查询房源详情的可修改字段(维护人能调用)
	 * @param busHouse
	 * */
	@Override
	public BusHouse detailUpdateInfo(BusHouse busHouse) {

		return busHouseMapper.detailUpdateInfo(busHouse);
	}
	/**
	 * 店长查询本店所有维护的房源
	 * @param map
	 * */
	@Override
	public List<BusHouse> listBusHouseRecord(Map<String, Object> map) {
		
		return busHouseMapper.listBusHouseRecord(map);
	}
	/**
	 * 变动价格添加价格记录
	 * @param map
	 * */
	@Override
	public void insertPriceLog(Map<String, Object> map) {
		busHouseMapper.insertPriceLog(map);
		
	}
	/**
	 * 验证是否是本店的
	 * */
	@Override
	public String isMyStore(Map<String, Object> map) {

		return busHouseMapper.isMyStore(map);
	}
	/**
	 * 分配维护人
	 * */
	@Override
	public void updateRecordUserName(Map<String, Object> map) {
		busHouseMapper.updateRecordUserName(map);
		
	}
	/**
	 * 分配录入人
	 * */
	@Override
	public void updateCreateUserName(Map<String, Object> map) {
		busHouseMapper.updateCreateUserName(map);
		
	}
	/**
	 * 分配实勘人
	 * */
	@Override
	public void updateExplorationUserName(Map<String, Object> map) {
		busHouseMapper.updateExplorationUserName(map);
		
	}
	/**
	 * 分配钥匙人
	 * */
	@Override
	public void updateKeyUserName(Map<String, Object> map) {
		busHouseMapper.updateKeyUserName(map);
		
	}

	@Override
	public List<CurrentUser> findByUserInfo(Map<String, Object> map) {
		return busHouseMapper.findByUserInfo(map);
	}
	/**
	 *设置为无效房源(下网)
	 * */
	@Override
	public void houseStateDown(String id) {
		busHouseMapper.houseStateDown(id);
		
	}

	@Override
	public void houseStateUp(Map<String, Object> map) {
		busHouseMapper.houseStateUp(map);
		
	}
}
