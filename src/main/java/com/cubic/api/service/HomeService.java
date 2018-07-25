package com.cubic.api.service;

import java.util.List;
import java.util.Map;

import com.cubic.api.model.BaseXiaoQu;
import com.cubic.api.model.BusHouse;
import com.cubic.api.model.User;
import com.cubic.api.model.home.CurrentScore;
import com.cubic.api.model.home.CurrentUser;

/**
 * @author cubic
 * @date 2018/07/13
 */
public interface HomeService {
	CurrentUser currentUser(Map<String,Object> map);
	CurrentUser findMyinfo(Map<String,Object> map);
	String updatePassword(Map<String,Object> map);
	List<CurrentScore> listScore();
	List<BaseXiaoQu> listHouseGood();
	List<BusHouse> listLatentScore(Map<String,Object> map);
	List<CurrentScore> listRankings(Map<String,Object> map);
	void upadateInfo(User user);
}
