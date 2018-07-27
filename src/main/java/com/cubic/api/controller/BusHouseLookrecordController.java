package com.cubic.api.controller;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cubic.api.core.response.Result;
import com.cubic.api.core.response.ResultGenerator;
import com.cubic.api.model.BusHouseLookrecord;
import com.cubic.api.model.BusHouseRecord;
import com.cubic.api.service.BusHouseLookrecordService;
import com.cubic.api.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author cubic
 * @date 2018/07/17
 */
@RestController
@RequestMapping("/vanke/house/lookrecord")
public class BusHouseLookrecordController {
    @Resource
    private BusHouseLookrecordService busHouseLookrecordService;
    @Resource
    private UserService userService;

    @PostMapping
    public Result add(@RequestBody BusHouseLookrecord busHouseLookrecord) {
    	busHouseLookrecordService.save(busHouseLookrecord);
        return ResultGenerator.genOkResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
    	busHouseLookrecordService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PutMapping
    public Result update(@RequestBody BusHouseLookrecord busHouseLookrecord) {
    	busHouseLookrecordService.update(busHouseLookrecord);
        return ResultGenerator.genOkResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
    	BusHouseLookrecord busHouseLookrecord = busHouseLookrecordService.findById(id);
        return ResultGenerator.genOkResult(busHouseLookrecord);
    }
    	/**
    	 * 查询带看记录
    	 * @param   map
    	 * @throws ParseException 
    	 * */
    @PreAuthorize("hasAuthority('houselookrecord:list')")
    @PostMapping("/list")
    public Result list(@RequestBody Map<String,Object> map) throws ParseException {
    	PageHelper.startPage(Integer.valueOf( map.get("page").toString()), Integer.valueOf( map.get("size").toString()));
        List<BusHouseLookrecord> list = busHouseLookrecordService.listBusHouseLookrecord(map);
        for(BusHouseLookrecord busHouseLookrecord:list){        	
         	SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         	Date date = fmt.parse(busHouseLookrecord.getCreateTime());
    		String  sre= fmt.format(date);    		
    		busHouseLookrecord.setCreateTime(sre);
        }
        PageInfo<BusHouseLookrecord> pageInfo = new PageInfo<BusHouseLookrecord>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
