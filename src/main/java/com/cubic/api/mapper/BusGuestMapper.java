package com.cubic.api.mapper;

import java.util.List;
import java.util.Map;

import com.cubic.api.core.mapper.MyMapper;
import com.cubic.api.model.BusGuest;

public interface BusGuestMapper extends MyMapper<BusGuest> {
	
	 /**
     * 创建客源信息
     * @param busGuest
     * 
     * */
	void insertBusGuest(BusGuest busGuest);
	 /**
     * 更新客源的跟进时间
     * @param busGuest
     * 
     * */
	void updateRecordTime(BusGuest busGuest);
	
	 /**
     * 转赠维护人
     * @param busGuest
     * 
     * */
	void updateRecordUser(BusGuest busGuest);
	 /**
     * 条件查询
     * @param map
     * 
     * */
	List<BusGuest> listBusGuest(Map<String,Object> map);
	
	
	 /**
     * 时间任务的查看所有上次维护时间距离现在时间大于10天或15天的客源
     * 
     * 
     * */
	List<BusGuest> listRecordTime();
	
	 /**
     * 按照条件查询成交客源
     * @param map
     * 
     * */	
	List<BusGuest> listTransactionGuest(Map<String,Object> map);
	
	 /**
     * 掉到共享池修改
     * @param busGuest
     * 
     * */
	void updateIsShareNull(BusGuest busGuest);
}