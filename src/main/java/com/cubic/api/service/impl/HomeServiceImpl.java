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
import com.cubic.api.model.User;
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
	public String updatePassword(Map<String, Object> map) {
		String pwd = (String)map.get("password");
		map.put("password", this.passwordEncoder.encode(pwd.trim()));
		
    	if(null!=map.get("oldpassword")&&!"".equals(map.get("oldpassword"))){  
    		String oldpwd=(String)map.get("oldpassword");
    		
    		User us=(User) map.get("userBean");
    		 //判断输入的旧密码和原来的密码一不一致
    	     boolean bl=passwordEncoder.matches(oldpwd, us.getPassword());
    		 if(bl){
    			mapper.updatePassword(map);
    			return "修改成功";
		     }
	    }
    	return "当前密码不正确";
		
		
	}

	@Override
	public List<CurrentScore> listRankings(Map<String, Object> map) {

		return mapper.listRankings(map);
	}

	@Override
	public void upadateInfo(User user) {
		mapper.upadateInfo(user);
		
	}

	@Override
	public void upadateImg(String url,String username) {
		mapper.upadateImg(url,username);
	}
	 

}
