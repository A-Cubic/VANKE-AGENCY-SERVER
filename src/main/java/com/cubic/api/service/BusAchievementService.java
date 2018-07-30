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
}
