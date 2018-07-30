package com.cubic.api.controller;

import com.cubic.api.core.response.Result;
import com.cubic.api.core.response.ResultGenerator;
import com.cubic.api.model.BusAchievement;
import com.cubic.api.service.BusAchievementService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author cubic
 * @date 2018/07/23
 */
@RestController
@RequestMapping("/vanke/achievement")
public class BusAchievementController {
    @Resource
    private BusAchievementService busAchievementService;

    @PostMapping
    public Result add(@RequestBody BusAchievement busAchievement) {
    	busAchievementService.save(busAchievement);
        return ResultGenerator.genOkResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
    	busAchievementService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PutMapping
    public Result update(@RequestBody BusAchievement busAchievement) {
    	busAchievementService.update(busAchievement);
        return ResultGenerator.genOkResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
    	BusAchievement busAchievement = busAchievementService.findById(id);
        return ResultGenerator.genOkResult(busAchievement);
    }
	/**
	 * 查询我的业绩信息OR店长权限的人查询本店的业绩信息
	 * @param map
	 * @throws ParseException 
	 * */
    @PreAuthorize("hasAuthority('achievement:listMyAchievement')")
    @PostMapping("/listMyAchievement")
    public Result listMyAchievement(Principal user,@RequestBody Map<String,Object> map) throws ParseException {
    	PageHelper.startPage(Integer.valueOf( map.get("page").toString()), Integer.valueOf( map.get("size").toString()));
    	 if(user.toString().indexOf("ROLE_USER")!=-1){ //经济人权限只能看自己的业绩   		
    		map.put("userNameOne", user.getName());
    	}else if(user.toString().indexOf("ROLE_MANAGER")!=-1){//店长权限看本店的业绩		
    		map.put("userName", user.getName());
    	}else{//测试数据用
    		map.put("userNameOne", user.getName());
    	}
    	
        List<BusAchievement> list = busAchievementService.listMyAchievement(map);
        
        
        for(BusAchievement busAchievement:list){
        	
        	//转换时间格式
         	SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         	Date date = fmt.parse(busAchievement.getCreateTime());
    		String  sre= fmt.format(date);
    		busAchievement.setCreateTime(sre);
        	if(!"".equals(busAchievement.getRoleName())||null!=busAchievement.getRoleName()){
        		String rolenametext=busAchievement.getRoleName();
        		rolenametext=rolenametext.replaceAll("1","录入人");
        		rolenametext=rolenametext.replaceAll("2","维护人");
        		rolenametext=rolenametext.replaceAll("3","钥匙人");
        		rolenametext=rolenametext.replaceAll("4","实勘人");
        		rolenametext=rolenametext.replaceAll("5","独家人");
        		rolenametext=rolenametext.replaceAll("6","促成人");
        		rolenametext=rolenametext.replaceAll("7","促成合作人");
        		rolenametext = rolenametext.substring(0,rolenametext.length() - 1);
        		busAchievement.setRoleName(rolenametext);
        	}
        }
        PageInfo<BusAchievement> pageInfo = new PageInfo<BusAchievement>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
	/**
	 * 审核成交业绩
	 * @param map
	 * @throws ParseException 
	 * */
    @PreAuthorize("hasAuthority('achievement:examineAchievement')")
    @PostMapping("/examineAchievement")
    public Result examineAchievement(Principal user,@RequestBody Map<String,Object> map){
    	PageHelper.startPage(Integer.valueOf( map.get("page").toString()), Integer.valueOf( map.get("size").toString()));
    	List<BusAchievement> list = busAchievementService.examineAchievement(map);
    	  for(BusAchievement busAchievement:list){
          	
          	if(!"".equals(busAchievement.getRoleName())||null!=busAchievement.getRoleName()){
          		String rolenametext=busAchievement.getRoleName();
          		rolenametext=rolenametext.replaceAll("1","录入人");
          		rolenametext=rolenametext.replaceAll("2","维护人");
          		rolenametext=rolenametext.replaceAll("3","钥匙人");
          		rolenametext=rolenametext.replaceAll("4","实勘人");
          		rolenametext=rolenametext.replaceAll("5","独家人");
          		rolenametext=rolenametext.replaceAll("6","促成人");
          		rolenametext=rolenametext.replaceAll("7","促成合作人");
          		rolenametext = rolenametext.substring(0,rolenametext.length() - 1);
          		busAchievement.setRoleName(rolenametext);
          	}
          }
    	PageInfo<BusAchievement> pageInfo = new PageInfo<BusAchievement>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
    

}
