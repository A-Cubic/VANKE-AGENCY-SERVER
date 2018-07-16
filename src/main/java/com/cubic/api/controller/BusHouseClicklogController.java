package com.cubic.api.controller;

import com.cubic.api.core.response.Result;
import com.cubic.api.core.response.ResultGenerator;
import com.cubic.api.model.BusHouseClicklog;
import com.cubic.api.service.BusHouseClicklogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author cubic
 * @date 2018/07/16
 */
@RestController
@RequestMapping("/vanke/house/clicklog")
public class BusHouseClicklogController {
    @Resource
    private BusHouseClicklogService busHouseClicklogService;

    @PostMapping
    public Result add(@RequestBody BusHouseClicklog busHouseClicklog) {
    	busHouseClicklogService.save(busHouseClicklog);
        return ResultGenerator.genOkResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
    	busHouseClicklogService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PutMapping
    public Result update(@RequestBody BusHouseClicklog busHouseClicklog) {
    	busHouseClicklogService.update(busHouseClicklog);
        return ResultGenerator.genOkResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
    	BusHouseClicklog busHouseClicklog = busHouseClicklogService.findById(id);
        return ResultGenerator.genOkResult(busHouseClicklog);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<BusHouseClicklog> list = busHouseClicklogService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
