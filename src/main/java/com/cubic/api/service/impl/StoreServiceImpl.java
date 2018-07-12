package com.cubic.api.service.impl;

import com.cubic.api.mapper.StoreMapper;
import com.cubic.api.model.Store;
import com.cubic.api.service.StoreService;
import com.cubic.api.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.Resource;

/**
 * @author cubic
 * @date 2018/07/10
 */
@Service
@Transactional
@SuppressWarnings("SpringJavaAutowiringInspection")
public class StoreServiceImpl extends AbstractService<Store> implements StoreService {
    @Resource
    private StoreMapper storeMapper;
    
    
 

}
