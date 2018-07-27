package com.cubic.api.service.impl;

import com.cubic.api.mapper.BusAchievementMapper;
import com.cubic.api.model.BusAchievement;
import com.cubic.api.service.BusAchievementService;
import com.cubic.api.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * @author cubic
 * @date 2018/07/23
 */
@Service
@Transactional
@SuppressWarnings("SpringJavaAutowiringInspection")
public class BusAchievementServiceImpl extends AbstractService<BusAchievement> implements BusAchievementService {
    @Resource
    private BusAchievementMapper busAchievementMapper;
	/**
	 * 查询我的业绩信息OR店长权限的人查询本店的业绩信息
	 * @param map
	 * */
	@Override
	public List<BusAchievement> listMyAchievement(Map<String, Object> map) {

		return busAchievementMapper.listMyAchievement(map);
	}



}
