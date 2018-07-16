package com.cubic.api.controller;

import com.cubic.api.core.response.Result;
import com.cubic.api.core.response.ResultGenerator;
import com.cubic.api.model.BusHouseRecord;
import com.cubic.api.service.BusHouseRecordService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
@RequestMapping("/vanke/house/record")
public class BusHouseRecordController {
    @Resource
    private BusHouseRecordService busHouseRecordService;

    @PostMapping("/insert")
    public Result add(Principal user,@RequestBody BusHouseRecord busHouseRecord) {
    	busHouseRecord.setUserName(user.getName());
    	busHouseRecordService.insertHouseRecord(busHouseRecord);
        return ResultGenerator.genOkResult("添加成功");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
    	busHouseRecordService.deleteById(id);
        return ResultGenerator.genOkResult();
    }
    /**
     * 设置跟进置顶
     * @param busHouseRecord
     * 
     * */
    @PostMapping("/updateIsTopOne")
    public Result updateIsTopOne(@RequestBody BusHouseRecord busHouseRecord) {
    	busHouseRecordService.updateHouseRecordIsTopOne(busHouseRecord);
        return ResultGenerator.genOkResult("置顶成功");
    }
    /**
     * 取消跟进置顶
     * @param busHouseRecord
     * 
     * */
    @PostMapping("/updateIsTopZero")
    public Result updateIsTopZero(@RequestBody BusHouseRecord busHouseRecord) {
    	busHouseRecordService.updateHouseRecordIsTopZero(busHouseRecord);
        return ResultGenerator.genOkResult("取消置顶");
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
    	BusHouseRecord busHouseRecord = busHouseRecordService.findById(id);
        return ResultGenerator.genOkResult(busHouseRecord);
    }
    /**
     * 查询跟进信息
     * @param map
     * 
     * */
    @PostMapping("/list")
    public Result list(@RequestBody Map<String,Object> map) {
    	PageHelper.startPage((Integer)map.get("page"), (Integer)map.get("size"));
        List<BusHouseRecord> list = busHouseRecordService.listHouseRecord(map);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
