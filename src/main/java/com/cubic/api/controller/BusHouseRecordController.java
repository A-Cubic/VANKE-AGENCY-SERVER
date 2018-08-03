package com.cubic.api.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cubic.api.core.response.Result;
import com.cubic.api.core.response.ResultGenerator;
import com.cubic.api.model.BusHouse;
import com.cubic.api.model.BusHouseRecord;
import com.cubic.api.service.BusHouseRecordService;
import com.cubic.api.service.BusHouseService;
import com.cubic.api.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author cubic
 * @date 2018/07/16
 */
@RestController
@RequestMapping("/vanke/house/record")
public class BusHouseRecordController {
    @Resource
    private BusHouseRecordService busHouseRecordService;
    @Resource
    private BusHouseService busHouseService;
    @Resource
    private UserService userService;
    @PreAuthorize("hasAuthority('houserecord:insert')")
    @PostMapping("/insert")
    public Result add(Principal user,@RequestBody BusHouseRecord busHouseRecord) {
    	busHouseRecord.setUserName(user.getName());
    	BusHouse busHouse=busHouseService.findById(busHouseRecord.getHouseId());
    	//判断当前跟进人是否是该房源的维护人
    	if(busHouse.getRecordUserName().equals(user.getName())){    		
    		BusHouse busHouseNew=new BusHouse();
    		busHouseNew.setId(busHouse.getId());
    		//更新跟进时间
    		busHouseService.updateRecordTime(busHouseNew);
    	}
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
    @PreAuthorize("hasAuthority('houserecord:updateIsTopOne')")
    @PostMapping("/updateIsTopOne")
    public Result updateIsTopOne(Principal user,@RequestBody BusHouseRecord busHouseRecord) {	
    		busHouseRecord.setTopicon("");
        	busHouseRecordService.updateHouseRecordIsTopOne(busHouseRecord);
            return ResultGenerator.genOkResult("置顶成功");	
    }
    /**
     * 取消跟进置顶
     * @param busHouseRecord
     * 
     * */
    @PreAuthorize("hasAuthority('houserecord:updateIsTopZero')")
    @PostMapping("/updateIsTopZero")
    public Result updateIsTopZero(@RequestBody BusHouseRecord busHouseRecord) {
    	busHouseRecord.setTopicon(null);
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
     * @throws ParseException 
     * 
     * */
    @PreAuthorize("hasAuthority('houserecord:list')")
    @PostMapping("/list")
    public Result list(@RequestBody Map<String,Object> map) throws ParseException {
    	PageHelper.startPage(Integer.valueOf( map.get("page").toString()), Integer.valueOf( map.get("size").toString()));
        List<BusHouseRecord> list = busHouseRecordService.listHouseRecord(map);
        for(BusHouseRecord busHouseRecord:list){        	
         	//转换时间格式
        	SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         	Date date = fmt.parse(busHouseRecord.getCreateTime());
    		String  sre= fmt.format(date);    		
    		busHouseRecord.setCreateTime(sre);
        }
        PageInfo<BusHouseRecord> pageInfo = new PageInfo<BusHouseRecord>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
