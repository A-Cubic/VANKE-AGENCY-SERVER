package com.cubic.api.service;

import java.util.List;
import com.cubic.api.model.BaseXiaoQu;
import com.cubic.api.model.home.CurrentScore;
import com.cubic.api.model.home.CurrentUser;

/**
 * @author cubic
 * @date 2018/07/13
 */
public interface HomeService {
	CurrentUser currentUser();
	List<CurrentScore> listScore();
	List<BaseXiaoQu> listHouseGood();
}