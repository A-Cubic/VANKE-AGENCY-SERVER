package com.cubic.api.mapper;

import com.cubic.api.core.mapper.MyMapper;
import com.cubic.api.model.BusHouse;

public interface BusHouseMapper extends MyMapper<BusHouse> {
	
	
	void UpdateBusHouseState(BusHouse busHouse);
}

