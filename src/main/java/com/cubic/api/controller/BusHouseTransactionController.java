package com.cubic.api.controller;

import com.cubic.api.core.response.Result;
import com.cubic.api.core.response.ResultGenerator;
import com.cubic.api.model.BusHouseTransaction;
import com.cubic.api.service.BusHouseTransactionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 * @author cubic
 * @date 2018/07/25
 */
@RestController
@RequestMapping("/vanke/house/transaction")
public class BusHouseTransactionController {
    @Resource
    private BusHouseTransactionService busHouseTransactionService;

    @PostMapping
    public Result add(@RequestBody BusHouseTransaction busHouseTransaction) {
    	busHouseTransactionService.save(busHouseTransaction);
    	//计算业绩
    	
    	
        return ResultGenerator.genOkResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
    	busHouseTransactionService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PutMapping
    public Result update(@RequestBody BusHouseTransaction busHouseTransaction) {
    	busHouseTransactionService.update(busHouseTransaction);
        return ResultGenerator.genOkResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
    	BusHouseTransaction busHouseTransaction = busHouseTransactionService.findById(id);
        return ResultGenerator.genOkResult(busHouseTransaction);
    }
    /**
     * 查询成交记录
     * @param map
     * */
    @PostMapping("/list")
    public Result list(Principal user,@RequestBody Map<String,Object> map) {
    	PageHelper.startPage(Integer.valueOf( map.get("page").toString()), Integer.valueOf( map.get("size").toString()));
        List<BusHouseTransaction> list = busHouseTransactionService.findAll();
        PageInfo<BusHouseTransaction> pageInfo = new PageInfo<BusHouseTransaction>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
