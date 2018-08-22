package com.cubic.api.controller;

import com.cubic.api.core.response.Result;
import com.cubic.api.core.response.ResultGenerator;
import com.cubic.api.model.BusHousePricelog;
import com.cubic.api.service.BusHousePricelogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author cubic
 * @date 2018/08/22
 */
@RestController
@RequestMapping("/vanke/house/pricelog")
public class BusHousePricelogController {
    @Resource
    private BusHousePricelogService busHousePricelogService;

//    @PostMapping
//    public Result add(@RequestBody BusHousePricelog busHousePricelog) {
//    	busHousePricelogService.save(busHousePricelog);
//        return ResultGenerator.genOkResult();
//    }
//
//    @DeleteMapping("/{id}")
//    public Result delete(@PathVariable Long id) {
//    	busHousePricelogService.deleteById(id);
//        return ResultGenerator.genOkResult();
//    }
//
//    @PutMapping
//    public Result update(@RequestBody BusHousePricelog busHousePricelog) {
//    	busHousePricelogService.update(busHousePricelog);
//        return ResultGenerator.genOkResult();
//    }
//
//    @GetMapping("/{id}")
//    public Result detail(@PathVariable Long id) {
//    	BusHousePricelog busHousePricelog = busHousePricelogService.findById(id);
//        return ResultGenerator.genOkResult(busHousePricelog);
//    }
    
    
	/**
	 * 查看房源修改记录
	 * */
    @PostMapping("/list")
    public Result list(@RequestBody Map<String,Object> map) {
    	PageHelper.startPage(Integer.valueOf( map.get("page").toString()), Integer.valueOf( map.get("size").toString()));
        List<BusHousePricelog> list = busHousePricelogService.listPriceLog(map);
        PageInfo<BusHousePricelog> pageInfo = new PageInfo<BusHousePricelog>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
