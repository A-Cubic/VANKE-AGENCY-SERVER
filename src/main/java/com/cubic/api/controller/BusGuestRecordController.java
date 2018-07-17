package com.cubic.api.controller;

import com.cubic.api.core.response.Result;
import com.cubic.api.core.response.ResultGenerator;
import com.cubic.api.model.BusGuest;
import com.cubic.api.model.BusGuestRecord;
import com.cubic.api.model.BusHouse;
import com.cubic.api.service.BusGuestRecordService;
import com.cubic.api.service.BusGuestService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 * @author cubic
 * @date 2018/07/16
 */
@RestController
@RequestMapping("/vanke/guest/record")
public class BusGuestRecordController {
    @Resource
    private BusGuestRecordService busGuestRecordService;
    @Resource
    private BusGuestService busGuestService;
    /**
     *添加跟进信息
     * @param busGuestRecord
     * 
     * */
    @PreAuthorize("hasAuthority('guestrecord:insert')")
    @PostMapping("/insert")
    public Result add(Principal user,@RequestBody BusGuestRecord busGuestRecord) {
    	busGuestRecord.setUserName(user.getName());    
    	BusGuest busGuest=busGuestService.findById(busGuestRecord.getGuestId());
    	BusGuest busGuestNew=new BusGuest();
    	busGuestNew.setId(busGuest.getId());
    	//更新客源跟进时间
    	busGuestService.updateRecordTime(busGuestNew);
    	
    	busGuestRecordService.insertGuestRecord(busGuestRecord);
        return ResultGenerator.genOkResult("添加成功");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
    	busGuestRecordService.deleteById(id);
        return ResultGenerator.genOkResult();
    }
    /**
     * 取消跟进置顶
     * @param busGuestRecord
     * 
     * */
    @PreAuthorize("hasAuthority('guestrecord:updateIsTopOne')")
    @PostMapping("/updateIsTopOne")
    public Result updateIsTopOne(@RequestBody BusGuestRecord busGuestRecord) {
    	busGuestRecordService.update(busGuestRecord);
    	  return ResultGenerator.genOkResult("置顶成功");
    }
    /**
     * 取消跟进置顶
     * @param busGuestRecord
     * 
     * */
    @PreAuthorize("hasAuthority('guestrecord:updateIsTopZero')")
    @PostMapping("/updateIsTopZero")
    public Result updateIsTopZero(@RequestBody BusGuestRecord busGuestRecord) {
    	busGuestRecordService.update(busGuestRecord);
        return ResultGenerator.genOkResult("取消置顶");
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
    	BusGuestRecord busGuestRecord = busGuestRecordService.findById(id);
        return ResultGenerator.genOkResult(busGuestRecord);
    }
    /**查询跟进信息
     * @param map
     * 
     * */
    @PreAuthorize("hasAuthority('guestrecord:list')")
    @PostMapping("/list")
    public Result list(@RequestBody Map<String,Object> map) {
    	PageHelper.startPage(Integer.valueOf( map.get("page").toString()), Integer.valueOf( map.get("size").toString()));
        List<BusGuestRecord> list = busGuestRecordService.listGuestRecord(map);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
