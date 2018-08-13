package com.cubic.api.mapper;

import java.util.List;
import java.util.Map;

import com.cubic.api.model.BaseRegion;
import com.cubic.api.model.BaseXiaoQu;
import com.cubic.api.model.BusHouse;
import com.cubic.api.model.User;
import com.cubic.api.model.home.CurrentScore;
import com.cubic.api.model.home.CurrentUser;

public interface HomeMapper {
	CurrentUser findByUser(Map<String,Object> map);
	CurrentUser findMyinfo(Map<String,Object> map);
	void updatePassword(Map<String,Object> map);
	List<BaseRegion> listRegions();
	List<BaseXiaoQu> listXiaoQu();
	List<BusHouse> listLatentScore(Map<String,Object> map);
	List<BusHouse> listLatentScoreStore(Map<String,Object> map);
	List<CurrentScore> listRankings(Map<String,Object> map);
	void upadateInfo(User user);
	void upadateImg(String avatar,String username);
	List<BusHouse> newHouseList();
	List<CurrentScore> myStoreRankings(Map<String,Object> map);
	List<CurrentScore> storeAllRankings(Map<String,Object> map);
	
	
}