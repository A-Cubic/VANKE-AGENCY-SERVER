package com.cubic.api.service.impl;

import com.cubic.api.mapper.BusGuestLookrecordMapper;
import com.cubic.api.model.BusGuestLookrecord;
import com.cubic.api.service.BusGuestLookrecordService;
import com.cubic.api.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author cubic
 * @date 2018/07/17
 */
@Service
@Transactional
@SuppressWarnings("SpringJavaAutowiringInspection")
public class BusGuestLookrecordServiceImpl extends AbstractService<BusGuestLookrecord> implements BusGuestLookrecordService {
    @Resource
    private BusGuestLookrecordMapper busGuestLookrecordMapper;

}
