package com.cubic.api.mapper;

import java.util.List;
import java.util.Map;

import com.cubic.api.core.mapper.MyMapper;
import com.cubic.api.model.BusHouse;

public interface BusHouseMapper extends MyMapper<BusHouse> {
	
	 /**
     * 按条件查询列表
     * @param map
     * 
     * */
	List<BusHouse> ListBusHouse(Map<String,Object> map);
	
	 /**
     * 创建房源
     * @param busHouse
     * 
     * */
	void insertBusHouse(BusHouse busHouse);
	
	 /**
     * 点击查看详细联系方式及房主姓名
     * @param map
     * 
     * */
	BusHouse DetailContacts(Map<String,Object> map);
	
	 /**
     * 点击查看房屋地址
     * @param map
     * 
     * */
	BusHouse DetailAddress(Map<String,Object> map);
	
	 /**
     * 更新房源跟进时间
     * @param busHouse
     * 
     * */
	void  updateRecordTime(BusHouse busHouse);
	
	 /**
     * 转赠维护人
     * @param busHouse
     * 
     * */
	void updateRecordUser(BusHouse busHouse);
	
	 /**
     * 改变钥匙持有人
     * @param busHouse
     * 
     * */
	void updateKey(BusHouse busHouse);
	
	
	 /**
     * 时间任务的查看所有上次维护时间距离现在时间大于10天或15天的房源
     * @param 
     * 
     * */
	List<BusHouse> listRecordTime();
	
	
	 /**
     * 查询我关注的房源
     * @param map
     * 
     * */
	List<BusHouse> listMyLikeHouse(Map<String,Object> map);
	
	 /**
     * 查询优质房源
     * @param map
     * 
     * */
	List<BusHouse> listIsfine(Map<String,Object> map);
	
	
	
}

