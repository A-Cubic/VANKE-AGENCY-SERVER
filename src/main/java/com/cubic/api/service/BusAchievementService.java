package com.cubic.api.service;

import com.cubic.api.model.BusAchievement;

import java.util.List;
import java.util.Map;

import com.cubic.api.core.service.Service;

/**
 * @author cubic
 * @date 2018/07/23
 */
public interface BusAchievementService extends Service<BusAchievement> {
	/**
	 * 查询自己的业绩
	 * @param map
	 * */
	List<BusAchievement> listMyAchievement(Map<String,Object> map);
	
	
	/**
	 * 审核成交业绩查询
	 * @param map
	 * */
	List<BusAchievement> examineAchievement(Map<String,Object> map);
	
	/**
	 * 修改审核状态
	 * @param BusAchievement
	 * */
	void updateExamineType(BusAchievement busAchievement);
	
	/**
	 * 创建业绩
	 * @param BusAchievement
	 * */
	void insertExamineAchievement(BusAchievement busAchievement);
	/**
	 * 查询业绩分配详情
	 * @param BusAchievement
	 * */
	List<BusAchievement> detailListAchievement(Map<String,Object> map);
	
	
	   
	   /**
	    * 成交业绩分配未通过,删除业绩
	    * @param long
	    * */
     void deleteTransactionAchievement(Long id);
     
     /**
      * 系统管理员查看分店的业绩
      * */
     List<BusAchievement> listAchievementTow(Map<String,Object> map);
     
     /**
      * 查询全部门店的业绩
      * */
     List<BusAchievement> listStoreAllAchievement(Map<String,Object> map);
}
