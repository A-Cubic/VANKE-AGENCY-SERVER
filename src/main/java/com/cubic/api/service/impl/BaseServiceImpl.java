package com.cubic.api.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cubic.api.mapper.BaseMapper;
import com.cubic.api.model.BaseRegion;
import com.cubic.api.model.BaseXiaoQu;
import com.cubic.api.service.BaseService;

/**
 * @author cubic
 * @date 2018/07/13
 */
@Service
@Transactional
public class BaseServiceImpl implements BaseService {

	@Resource
	private BaseMapper mapper;
	 
	@Override
	public List<BaseRegion> listRegions() {
		return mapper.listRegions();
	}

	@Override
	public List<BaseXiaoQu> listXiaoQu() {
		return mapper.listXiaoQu();
	}

}
