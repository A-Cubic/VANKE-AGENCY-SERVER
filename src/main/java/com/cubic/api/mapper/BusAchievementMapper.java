package com.cubic.api.mapper;

import java.util.List;
import java.util.Map;

import com.cubic.api.core.mapper.MyMapper;
import com.cubic.api.model.BusAchievement;

public interface BusAchievementMapper extends MyMapper<BusAchievement> {
	
	/**
	 * 查询我的业绩信息OR店长权限的人查询本店的业绩信息
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
	
   List<BusAchievement> detailListAchievement(Map<String,Object> map);
	
}