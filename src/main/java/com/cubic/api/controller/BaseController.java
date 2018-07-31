package com.cubic.api.controller;

import java.security.Principal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cubic.api.core.response.Result;
import com.cubic.api.core.response.ResultGenerator;
import com.cubic.api.model.BaseRegion;
import com.cubic.api.model.BaseXiaoQu;
import com.cubic.api.model.Store;
import com.cubic.api.service.BaseService;

/**
 * 基础数据接口
 * @author fei.yu
 * @date 2018/07/17
 */
@RestController
@RequestMapping("/vanke/base")
public class BaseController {
    @Resource
    private BaseService service;

    /**
     * 地市区街道(级联查询)
     * 
     * */
    @PostMapping("/region/list")
    public Result listRegions() {
        List<BaseRegion> list = service.listRegions();
        return ResultGenerator.genOkResult(list);
    }
    
    /**
     * 小区(筛选)
     * 
     * */
    @PostMapping("/xiaoqu/list")
    public Result listXiaoQu() {
        List<BaseXiaoQu> list = service.listXiaoQu();
        return ResultGenerator.genOkResult(list);
    }
    
    /**
     * 门店下拉
     * 
     * */
    @PostMapping("/mendian/list")
    public Result listMendian(Principal user) {
        List<Store> list = service.listMendian();
        return ResultGenerator.genOkResult(list);
    }
}
