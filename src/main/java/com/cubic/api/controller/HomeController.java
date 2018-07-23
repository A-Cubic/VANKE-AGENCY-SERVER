package com.cubic.api.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cubic.api.core.response.Result;
import com.cubic.api.core.response.ResultGenerator;
import com.cubic.api.model.home.CurrentScore;
import com.cubic.api.model.home.CurrentUser;
import com.cubic.api.service.HomeService;

/**
 * 首页
 * @author fei.yu
 * @date 2018/07/17
 */
@RestController
@RequestMapping("/vanke/home")
public class HomeController {
    @Resource
    private HomeService service;

    /**
     * 首页用户信息
     * 
     * */
    @PostMapping("/user/info")
    public Result currentUser(Principal users) {
    	Map<String,Object> map=new HashMap<String,Object>();
    	map.put("username", users.getName());
        CurrentUser user = service.currentUser(map);
        return ResultGenerator.genOkResult(user);
    }
    
    /**
     * 首页排行榜
     * 
     * */
    @PostMapping("/score/list")
    public Result listScore(final Principal user,@RequestBody CurrentScore score) {
        List<CurrentScore> list = service.listScore();
        return ResultGenerator.genOkResult(list);
    }
    
    /**
     * 首页优质房源
     * 
     * */
    @PostMapping("/house/list")
    public Result listHouseGood() {
//        List<BaseXiaoQu> list = service.listXiaoQu();
        return ResultGenerator.genOkResult();
    }
    
    /**
     * 首页最新资讯
     * 暂无
     * */
    @PostMapping("/news/list")
    public Result listNews() {
        return ResultGenerator.genOkResult();
    }
    
}
