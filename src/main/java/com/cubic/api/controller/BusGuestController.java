package com.cubic.api.controller;

import com.cubic.api.core.response.Result;
import com.cubic.api.core.response.ResultGenerator;
import com.cubic.api.model.BusGuest;
import com.cubic.api.service.BusGuestService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

    @PostMapping
    public Result add(@RequestBody BusGuest busGuest) {
    	busGuestService.save(busGuest);//G
        return ResultGenerator.genOkResult();
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

    @PostMapping
    public Result list(@RequestBody Map<String,Object> map) {
    	PageHelper.startPage((Integer)map.get("page"), (Integer)map.get("size"));
        List<BusGuest> list = busGuestService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
