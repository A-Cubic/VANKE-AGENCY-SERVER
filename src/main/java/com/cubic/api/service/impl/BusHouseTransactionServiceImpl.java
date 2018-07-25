package com.cubic.api.service.impl;

import com.cubic.api.mapper.BusHouseTransactionMapper;
import com.cubic.api.model.BusHouseTransaction;
import com.cubic.api.service.BusHouseTransactionService;
import com.cubic.api.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author cubic
 * @date 2018/07/25
 */
@Service
@Transactional
@SuppressWarnings("SpringJavaAutowiringInspection")
public class BusHouseTransactionServiceImpl extends AbstractService<BusHouseTransaction> implements BusHouseTransactionService {
    @Resource
    private BusHouseTransactionMapper busHouseTransactionMapper;

}
