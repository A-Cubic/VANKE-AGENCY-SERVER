package com.cubic.api.mapper;

import java.util.List;
import java.util.Map;

import com.cubic.api.model.BaseRegion;
import com.cubic.api.model.BaseXiaoQu;
import com.cubic.api.model.home.CurrentUser;

public interface HomeMapper {
	CurrentUser findByUser(Map<String,Object> map);
	List<BaseRegion> listRegions();
	List<BaseXiaoQu> listXiaoQu();
}