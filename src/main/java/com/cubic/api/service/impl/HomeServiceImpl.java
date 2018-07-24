package com.cubic.api.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cubic.api.mapper.HomeMapper;
import com.cubic.api.model.BaseXiaoQu;
import com.cubic.api.model.BusHouse;
import com.cubic.api.model.home.CurrentScore;
import com.cubic.api.model.home.CurrentUser;
import com.cubic.api.service.HomeService;

/**
 * @author cubic
 * @date 2018/07/13
 */
@Service
@Transactional
public class HomeServiceImpl implements HomeService {
    @Resource
    private PasswordEncoder passwordEncoder;
	@Resource
	private HomeMapper mapper;

	@Override
	public CurrentUser currentUser(Map<String,Object> map) {

		return mapper.findByUser(map);
	}

	@Override
	public List<CurrentScore> listScore() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BaseXiaoQu> listHouseGood() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BusHouse> listLatentScore(Map<String, Object> map) {

		return mapper.listLatentScore(map);
	}

	@Override
	public CurrentUser findMyinfo(Map<String, Object> map) {

		return mapper.findMyinfo(map);
	}

	@Override
	public void updatePassword(Map<String, Object> map) {
		String pwd = (String)map.get("password");
		map.put("password", this.passwordEncoder.encode(pwd.trim()));
		mapper.updatePassword(map);
		
	}

	@Override
	public List<CurrentScore> listRankings(Map<String, Object> map) {

		return mapper.listRankings(map);
	}
	 

}
