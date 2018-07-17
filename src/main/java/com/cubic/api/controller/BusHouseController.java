package com.cubic.api.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cubic.api.core.response.Result;
import com.cubic.api.core.response.ResultGenerator;
import com.cubic.api.model.BusHouse;
import com.cubic.api.service.BusHouseClicklogService;
import com.cubic.api.service.BusHouseService;
import com.cubic.api.util.NumberUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.cubic.api.model.BusHouseClicklog;



/**
 * 房源接口
 * @author cubic
 * @date 2018/07/12
 */
@RestController
@RequestMapping("/vanke/house")
public class BusHouseController {
    @Resource
    private BusHouseService busHouseService;
    @Resource
    private BusHouseClicklogService busHouseClicklogService;

   /**
    * 创建房源信息
    * @param busHouse
    * 
    * */
    @PreAuthorize("hasAuthority('house:save')")
    @PostMapping("/save")
    public Result add(Principal user,@RequestBody BusHouse busHouse) {
    	//创建人账号名
    	busHouse.setCreateUserName(user.getName());
    	//维护人账号名
    	busHouse.setRecordUserName(user.getName());
    	busHouseService.insertBusHouse(busHouse);

    	//根据id得到房源编号并更新
    	String num = NumberUtil.geoEquipmentNo("H",busHouse.getId());
    	BusHouse busHouseNew=new BusHouse();
    	busHouseNew.setId(busHouse.getId());
    	busHouseNew.setNumber(num);
    	busHouseService.update(busHouseNew);
    	
        return ResultGenerator.genOkResult("添加成功");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
    	busHouseService.deleteById(id);
        return ResultGenerator.genOkResult();
    }
    
    /**
     * 改变房源状态
     * @param busHouse
     * 状态:(0:普通,1:特殊,2:无效,3:已售出/已租出)
     * */
    @PreAuthorize("hasAuthority('house:updateState')")
    @PostMapping("/updateState")
    public Result updateState(@RequestBody BusHouse busHouse) {
//    	BusHouse busHouseNew=new BusHouse();
//    	if(null != busHouse){
//    		if(busHouse.getId() != null && busHouse.getState() != null){
//    			busHouseNew.setId(busHouse.getId());
//    			busHouseNew.setState(busHouse.getState());
//    			busHouseService.update(busHouseNew);
//    		}
//    	}
    	//提交到审核
        return ResultGenerator.genOkResult("提交审核成功");
    }
    
    /**
     * 转赠房源维护人
     * @param busHouse
     * 
     * */
    @PreAuthorize("hasAuthority('house:updateRecordUser')")
    @PostMapping("/updateRecordUser")
    public Result updateRecordUser(@RequestBody BusHouse busHouse) {
    	BusHouse busHouseNew=new BusHouse();
    	if(null != busHouse){
    		if(busHouse.getId() != null && busHouse.getRecordUserName() != null){
    			busHouseNew.setId(busHouse.getId());
    			busHouseNew.setRecordUserName(busHouse.getRecordUserName());
    			busHouseService.update(busHouseNew);
    		}
    	}
        return ResultGenerator.genOkResult("修改成功");
    }
    /**
     * 修改房源基本信息
     * @param busHouse
     * 
     * */
    @PreAuthorize("hasAuthority('house:update')")
    @PostMapping("/update")
    public Result update(@RequestBody BusHouse busHouse) {
    	
    	if(null != busHouse){
    			busHouseService.update(busHouse);  		
    	}
        return ResultGenerator.genOkResult("修改成功");
    }
    
    
    /**
     * 录入实勘图片
     * @param busHouse
     * 
     * */
    @PreAuthorize("hasAuthority('house:updateImg')")
    @PostMapping("/updateImg")
    public Result updateImg(Principal user,@RequestBody BusHouse busHouse) {
    	
//    	if(null != busHouse){
//    			busHouseService.update(busHouse);  		
//    	}
        return ResultGenerator.genOkResult("录入成功");
    }
  
    
    /**
     * 更新钥匙状态和拥有者
     * 
     * ,@RequestBody BusHouse busHouse
     * */
    @PreAuthorize("hasAuthority('house:updateKey')")
    @PostMapping("/updateKey")
    public Result updateKey(Principal user) {
    	BusHouse busHouse=new BusHouse();
    		busHouse.setIskey("1");
    		busHouse.setKeyUserName(user.getName());
  			busHouseService.update(busHouse);  		

        return ResultGenerator.genOkResult("更改成功");
    }
	 /**
     * 点击查看详细联系方式及房主姓名
     * @param map
     * 
     * */
    @PreAuthorize("hasAuthority('house:detailPhone')")
    @PostMapping("/detailPhone")
    public Result detailPhone(Principal user,@RequestBody Map<String,Object> map) {
    	BusHouse busHouse = busHouseService.DetailContacts(map);
    	if(busHouse.getClickcount()==20){
    		return ResultGenerator.genOkResult("您今日查询联系信息的次数已用完");   		
    	}else if (busHouse.getClickcount()<20){
    		
    		BusHouseClicklog busHouseClicklog=new BusHouseClicklog();
    		busHouseClicklog.setClickUserName(user.getName());
    		busHouseClicklog.setHouseId(Long.parseLong(map.get("houseId").toString()));
    		busHouseClicklog.setRecordUserName(busHouse.getRecordUserName());
    		busHouseClicklog.setType("1");
    		//记录查询日志
    		busHouseClicklogService.insertClickLog(busHouseClicklog);
    		busHouse.setRecordUserName(null);
    	}
    	return ResultGenerator.genOkResult(busHouse);
    }
	
	 /**
    * 点击查看房屋地址
    * @param map
    * 
    * */
    @PreAuthorize("hasAuthority('house:detailAddress')")
    @PostMapping("/detailAddress")
    public Result detail(Principal user,@RequestBody Map<String,Object> map) {
    	BusHouse busHouse = busHouseService.DetailAddress(map);
    	if(busHouse.getClickcount()==20){
    		return ResultGenerator.genOkResult("您今日查询房屋地址的次数已用完");
    	}else if (busHouse.getClickcount()<20){
    		BusHouseClicklog busHouseClicklog=new BusHouseClicklog();
    		busHouseClicklog.setClickUserName(user.getName());
    		busHouseClicklog.setHouseId(Long.parseLong(map.get("houseId").toString()));
    		busHouseClicklog.setRecordUserName(busHouse.getRecordUserName());
    		busHouseClicklog.setType("2");
    		//记录查询日志
    		busHouseClicklogService.insertClickLog(busHouseClicklog);
    		busHouse.setRecordUserName(null);
    		
    	}
        return ResultGenerator.genOkResult(busHouse);
    }

    /**
     * 按条件查询列表 返回分页数据
     * @param  page  size map
     * @RequestBody Map<String,Object> map
     * */
    @PreAuthorize("hasAuthority('house:list')")
    @PostMapping("/list")
    public Result list(Principal user,@RequestBody Map<String,Object> map) {
    	map.put("userName", user.getName());
        PageHelper.startPage(Integer.valueOf( map.get("page").toString()), Integer.valueOf( map.get("size").toString()));
        List<BusHouse> list = busHouseService.ListBusHouse(map);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
    
    /**
     * 测试接口
     * @param map
     * 
     * */
    @PostMapping("/test")
    public Result test(@RequestBody Map<String,Object> map) {
        return ResultGenerator.genOkResult(map);
    }
}
