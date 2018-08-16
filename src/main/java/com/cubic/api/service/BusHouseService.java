package com.cubic.api.service;

import com.cubic.api.model.BusHouse;
import com.cubic.api.model.home.CurrentUser;

import java.util.List;
import java.util.Map;

import com.cubic.api.core.service.Service;

/**
 * @author cubic
 * @date 2018/07/12
 */
public interface BusHouseService extends Service<BusHouse> {

	
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
     * 掉到共享池清空维护人列
     * @param busHouse
     * 
     * */
	void updateIsShareNull(BusHouse busHouse);
	
	
	 /**
     * 改变钥匙持有人
     * @param busHouse
     * 
     * */
	void updateKey(BusHouse busHouse);
	/**
	 * 取消钥匙人
	 * 
	 * @param busHouse
	 * 
	 */
	void updateCancelKey(BusHouse busHouse);
	
	 /**
     * 时间任务的查看所有上次维护时间距离现在时间大于10天或15天的房源
     * @param map
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
	List<BusHouse> listIsfine(Map<String,String> map);
	
	 /**
     * 查询详细房源
     * @param map
     * 
     * */
	BusHouse detailHouse(Map<String,String> map);
	
	/**
     * 修改维护人（共享池）
     * @param busHouse
     * 
     * */
	void updateShareState(BusHouse busHouse);
	/**
	 * 查询验证地址是否已存在
	 * 
	 * @param map
	 * 
	 */
	List<BusHouse> findIsAddress(BusHouse busHouse);
	
	/**
	 * 查询房源详情要可修改的字段信息(维护人能调用)
	 * 
	 * @param busHouse
	 * 
	 */
	BusHouse detailUpdateInfo(BusHouse busHouse);
	
	
	/**
	 * 店长查询本店所有维护的房源
	 * 
	 * @param map
	 * 
	 */
	List<BusHouse> listBusHouseRecord(Map<String, Object> map);
	
	/**
	 * 变动价格添加价格记录
	 * 
	 * @param map
	 * 
	 */
	void insertPriceLog(Map<String, Object> map);
	
	
	
	/**
	 * 验证是否是本店的
	 * @param map
	 * */
	String isMyStore(Map<String, Object> map);
	
	/**
	 * 分配维护人
	 * @param map
	 * */
	void updateRecordUserName(Map<String, Object> map);
	
	/**
	 * 分配录入人
	 * @param map
	 * */
	void updateCreateUserName(Map<String, Object> map);

	
	/**
	 * 分配实勘人
	 * @param map
	 * */
	void updateExplorationUserName(Map<String, Object> map);

	
	/**
	 * 分配钥匙人
	 * @param map
	 * */
	void updateKeyUserName(Map<String, Object> map);
	
	
	/**
	 * 分配列表
	 * @param map
	 * */
	List<CurrentUser> findByUserInfo(Map<String, Object> map);
}
