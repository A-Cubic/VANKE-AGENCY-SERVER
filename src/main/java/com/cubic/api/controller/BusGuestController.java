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
import com.cubic.api.model.BusGuest;
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

    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
    	BusGuest busGuest = busGuestService.findById(id);
        return ResultGenerator.genOkResult(busGuest);
    }
    @PreAuthorize("hasAuthority('guest:list')")
    @PostMapping("/list")
    public Result list(@RequestBody Map<String,Object> map) {
    	  PageHelper.startPage(Integer.valueOf( map.get("page").toString()), Integer.valueOf( map.get("size").toString()));
        List<BusGuest> list = busGuestService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
