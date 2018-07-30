package com.cubic.api.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cubic.api.core.response.Result;
import com.cubic.api.core.response.ResultGenerator;
import com.cubic.api.model.BusGuestLookrecord;
import com.cubic.api.model.BusHouseLookrecord;
import com.cubic.api.service.BusGuestLookrecordService;
import com.cubic.api.service.BusHouseLookrecordService;
import com.cubic.api.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author cubic
 * @date 2018/07/17
 */
@RestController
@RequestMapping("/vanke/guest/lookrecord")
public class BusGuestLookrecordController {
    @Resource
    private BusGuestLookrecordService busGuestLookrecordService;
    @Resource
    private BusHouseLookrecordService busHouseLookrecordService;
    @Resource
    private UserService userService;
    /**
     * 创建客源的带看信息
     * */
    @PreAuthorize("hasAuthority('guestlookrecord:insert')")
    @PostMapping("/insert")
    public Result add(Principal user,@RequestBody List<BusGuestLookrecord> busGuestLookrecords) {
    	//创建客源带看多条
    	
    
    	for(BusGuestLookrecord busGuestLookrecord:busGuestLookrecords){
    		busGuestLookrecord.setUserName(user.getName());
    		busGuestLookrecordService.save(busGuestLookrecord);
	    	//同时创建房源的带看信息
	    	BusHouseLookrecord busHouseLookrecord=new BusHouseLookrecord();
	    	busHouseLookrecord.setHouseId(busGuestLookrecord.getHouseId());
	    	busHouseLookrecord.setUserName(user.getName());
	    	busHouseLookrecord.setCreateTime(busGuestLookrecord.getCreateTime());
	    	busHouseLookrecord.setEndtime(busGuestLookrecord.getEndtime());
	    
	    	//创建房源带看多条
	    	busHouseLookrecordService.save(busHouseLookrecord);
	    	
    	}
    	
        return ResultGenerator.genOkResult("添加成功");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
    	busGuestLookrecordService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PutMapping
    public Result update(@RequestBody BusGuestLookrecord busGuestLookrecord) {
    	busGuestLookrecordService.update(busGuestLookrecord);
        return ResultGenerator.genOkResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
    	BusGuestLookrecord busGuestLookrecord = busGuestLookrecordService.findById(id);
        return ResultGenerator.genOkResult(busGuestLookrecord);
    }
    
    /**
     *查询带看客源信息
     *@param map
     * */
    @PreAuthorize("hasAuthority('guestlookrecord:list')")
    @PostMapping("/list")
    public Result list(@RequestBody Map<String,Object> map) {
    	PageHelper.startPage(Integer.valueOf( map.get("page").toString()), Integer.valueOf( map.get("size").toString()));
        List<BusGuestLookrecord> list = busGuestLookrecordService.listBusGuestLookrecord(map); 
        PageInfo<BusGuestLookrecord> pageInfo = new PageInfo<BusGuestLookrecord>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
