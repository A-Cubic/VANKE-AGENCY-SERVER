package com.cubic.api.service.impl;

import com.cubic.api.mapper.BusHouseMapper;
import com.cubic.api.model.BusHouse;
import com.cubic.api.service.BusHouseService;
import com.cubic.api.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author cubic
 * @date 2018/07/12
 */
@Service
@Transactional
@SuppressWarnings("SpringJavaAutowiringInspection")
public class BusHouseServiceImpl extends AbstractService<BusHouse> implements BusHouseService {
    @Resource
    private BusHouseMapper busHouseMapper;

}
