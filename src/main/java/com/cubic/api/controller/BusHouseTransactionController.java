package com.cubic.api.controller;

import com.cubic.api.core.response.Result;
import com.cubic.api.core.response.ResultGenerator;
import com.cubic.api.model.BusHouseTransaction;
import com.cubic.api.service.BusHouseTransactionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author cubic
 * @date 2018/07/25
 */
@RestController
@RequestMapping("/bus/house/transaction")
public class BusHouseTransactionController {
    @Resource
    private BusHouseTransactionService busHouseTransactionService;

    @PostMapping
    public Result add(@RequestBody BusHouseTransaction busHouseTransaction) {
busHouseTransactionService.save(busHouseTransaction);
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

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<BusHouseTransaction> list = busHouseTransactionService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
