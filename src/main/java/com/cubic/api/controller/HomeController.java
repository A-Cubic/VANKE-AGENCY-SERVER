package com.cubic.api.controller;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.cubic.api.model.BusHouse;
import com.cubic.api.model.home.CurrentScore;
import com.cubic.api.model.home.CurrentUser;
import com.cubic.api.service.HomeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

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
        
        List<BusHouse> list=service.listLatentScore(map);
        int latent=0;
        //计算潜在业绩
        for(BusHouse busHouse:list){
        	double percent=0;
        	if(users.getName().equals(busHouse.getCreateUserName())){
        		percent=percent+0.1;
        	}
        	if(users.getName().equals(busHouse.getRecordUserName())){
        		percent=percent+0.1;
        	}
        	if(users.getName().equals(busHouse.getExplorationUserName())){
        		percent=percent+0.1;
        		
        	}
        	if(users.getName().equals(busHouse.getKeyUserName())){
        		percent=percent+0.05;
        	}
        	//计算单个房子的潜在业绩=(房源报价*百分之3)*角色占比(最大百分之35)
        	int  result=(int) ((int) (Integer.parseInt(busHouse.getPrice())*0.03)*percent);
        	latent=latent+result;
        }
        //潜在业绩
        user.setLatent_score(String.valueOf(latent));
        return ResultGenerator.genOkResult(user);
    }
    
    
    /**
     * 个人信息
     * @throws ParseException 
     * 
     * */
    @PostMapping("/user/findMyinfo")
    public Result findMyinfo(final Principal user) throws ParseException {
    	Map<String,Object> map=new HashMap<String,Object>();
    	map.put("username", user.getName());
    	CurrentUser users = service.findMyinfo(map);
    	SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
    	Date date = fmt.parse(users.getRegister_time());  
    	String sre = fmt.format(date);  
    	users.setRegister_time(sre);
        return ResultGenerator.genOkResult(users);
    }
    
    @PostMapping("/user/updatePassword")
    public Result updatePassword(final Principal user,@RequestBody Map<String,Object> map) {
    	map.put("username", user.getName());
    	
    	
    	service.updatePassword(map);
        return ResultGenerator.genOkResult("修改成功");
    }
    /**
     * 首页排行榜
     * 
     * */
    @PostMapping("/score/list")
    public Result listScore(final Principal user,@RequestBody Map<String,Object> map) {
    	PageHelper.startPage(Integer.valueOf( map.get("page").toString()), Integer.valueOf( map.get("size").toString()));
        List<CurrentScore> list = service.listRankings(map);
        PageInfo<CurrentScore> pageInfo = new PageInfo<CurrentScore>(list);
        return ResultGenerator.genOkResult(pageInfo);
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
