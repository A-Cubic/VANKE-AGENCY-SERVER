package com.cubic.api.mapper;

import java.util.List;
import java.util.Map;

import com.cubic.api.core.mapper.MyMapper;
import com.cubic.api.model.BusAchievement;

public interface BusAchievementMapper extends MyMapper<BusAchievement> {
	
	/**
	 * 查询自己的业绩
	 * @param map
	 * */
	List<BusAchievement> listMyAchievement(Map<String,Object> map);
	
	/**
	 * 查询排行
	 * @param map
	 * */
	List<BusAchievement>  listAchievement(Map<String,Object> map);
}