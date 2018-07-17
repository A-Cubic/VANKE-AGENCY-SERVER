package com.cubic.api.controller;

import com.cubic.api.core.response.Result;
import com.cubic.api.core.response.ResultGenerator;
import com.cubic.api.model.BusHouseLookrecord;
import com.cubic.api.service.BusHouseLookrecordService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author cubic
 * @date 2018/07/17
 */
@RestController
@RequestMapping("/vanke/house/lookrecord")
public class BusHouseLookrecordController {
    @Resource
    private BusHouseLookrecordService busHouseLookrecordService;

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
    	 * */
    @PostMapping("/list")
    public Result list(@RequestBody Map<String,Object> map) {
    	PageHelper.startPage(Integer.valueOf( map.get("page").toString()), Integer.valueOf( map.get("size").toString()));
        List<BusHouseLookrecord> list = busHouseLookrecordService.listBusHouseLookrecord(map);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
