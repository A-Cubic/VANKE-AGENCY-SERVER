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
import com.cubic.api.model.BusExamine;
import com.cubic.api.model.BusGuest;
import com.cubic.api.service.BusExamineService;
import com.cubic.api.service.BusGuestService;
import com.cubic.api.util.NumberUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 客源接口
 * @author cubic
 * @date 2018/07/13
 */
@RestController
@RequestMapping("/vanke/guest")
public class BusGuestController {
    @Resource
    private BusGuestService busGuestService;
    @Resource
    private BusExamineService busExamineService;
    /**
     * 创建客源
     * @param busGuest
     * 
     * */
    @PreAuthorize("hasAuthority('guest:insert')")
    @PostMapping("/insert")
    public Result add(Principal user,@RequestBody BusGuest busGuest) {
       	//创建人账号名
    	busGuest.setCreateUserName(user.getName());
    	//维护人账号名
    	busGuest.setRecordUserName(user.getName());
    	busGuestService.insertBusGuest(busGuest);
    	
    	//根据id得到客源编号并更新
    	String num = NumberUtil.geoEquipmentNo("G",busGuest.getId());
    	BusGuest busGuestNew=new BusGuest();
    	busGuestNew.setId(busGuest.getId());
    	busGuestNew.setNumber(num);
    	busGuestService.update(busGuestNew);
        return ResultGenerator.genOkResult("添加成功");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
    	busGuestService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PutMapping
    public Result update(@RequestBody BusGuest busGuest) {
    	busGuestService.update(busGuest);
        return ResultGenerator.genOkResult();
    }
    /**
     * 设置为无效房源
     * @param busGuest
     * 
     * */
    @PreAuthorize("hasAuthority('guest:updateState')")
    @PostMapping("/updateState")
    public Result updateState(Principal user,@RequestBody BusGuest busGuest) {
    	//审核信息
    	BusExamine busExamine=new BusExamine();
    	busExamine.setGuestId(busGuest.getId());
    	busExamine.setType("5");
    	busExamine.setUserName(user.getName());
    	busExamineService.insertBusExamine(busExamine);
        return ResultGenerator.genOkResult("提交审核成功");
    }
    
    /**
     * 设置为无效房源
     * @param busGuest
     * 
     * */
    @PreAuthorize("hasAuthority('guest:updateRecordUser')")
    @PostMapping("/updateRecordUser")
    public Result updateRecordUser(Principal user,@RequestBody BusGuest busGuest) {
    	BusGuest busGuestNew=new BusGuest();
    	if(null != busGuest){
    		if(busGuest.getId() != null && busGuest.getRecordUserName() != null){
    			busGuestNew.setId(busGuest.getId());
    			busGuestNew.setRecordUserName(busGuest.getRecordUserName());
    			busGuestService.updateRecordUser(busGuestNew);
    		}
    	}
        return ResultGenerator.genOkResult("修改成功");
    }
    /**
     * 详情查询
     * @param map
     * 
     * */
    @PreAuthorize("hasAuthority('guest:detail')")
    @PostMapping("/detail")
    public Result detail(@RequestBody Map<String,Object> map) {
    	BusGuest busGuest = busGuestService.findById(map.get("id"));
        return ResultGenerator.genOkResult(busGuest);
    }
    /**
     * 客源条件查询
     * @param map
     * 
     * */
    @PreAuthorize("hasAuthority('guest:list')")
    @PostMapping("/list")
    public Result list(Principal user,@RequestBody Map<String,Object> map) {
    	PageHelper.startPage(Integer.valueOf( map.get("page").toString()), Integer.valueOf( map.get("size").toString()));
    	map.put("recordUserName", user.getName());
    	List<BusGuest> list = busGuestService.listBusGuest(map);
        PageInfo<BusGuest> pageInfo = new PageInfo<BusGuest>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
