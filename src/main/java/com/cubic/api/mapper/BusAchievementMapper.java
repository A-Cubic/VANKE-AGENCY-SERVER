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
	
}