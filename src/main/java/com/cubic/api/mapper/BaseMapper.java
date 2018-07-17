package com.cubic.api.mapper;

import java.util.List;
import com.cubic.api.model.BaseRegion;
import com.cubic.api.model.BaseXiaoQu;

public interface BaseMapper {
	List<BaseRegion> listRegions();
	List<BaseXiaoQu> listXiaoQu();
}