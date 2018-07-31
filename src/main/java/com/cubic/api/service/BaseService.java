package com.cubic.api.service;

import java.util.List;

import com.cubic.api.model.BaseRegion;
import com.cubic.api.model.BaseXiaoQu;
import com.cubic.api.model.Store;

/**
 * @author cubic
 * @date 2018/07/13
 */
public interface BaseService {
	List<BaseRegion> listRegions();
	List<BaseXiaoQu> listXiaoQu();
	List<Store> listMendian();
}
