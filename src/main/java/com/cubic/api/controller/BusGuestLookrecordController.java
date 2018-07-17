package com.cubic.api.controller;

import com.cubic.api.core.response.Result;
import com.cubic.api.core.response.ResultGenerator;
import com.cubic.api.model.BusGuestLookrecord;
import com.cubic.api.service.BusGuestLookrecordService;
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
@RequestMapping("/vanke/guest/lookrecord")
public class BusGuestLookrecordController {
    @Resource
    private BusGuestLookrecordService busGuestLookrecordService;

    @PostMapping("/insert")
    public Result add(@RequestBody BusGuestLookrecord busGuestLookrecord) {
    	busGuestLookrecordService.save(busGuestLookrecord);
        return ResultGenerator.genOkResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
    	busGuestLookrecordService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PutMapping
    public Result update(@RequestBody BusGuestLookrecord busGuestLookrecord) {
    	busGuestLookrecordService.update(busGuestLookrecord);
        return ResultGenerator.genOkResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
    	BusGuestLookrecord busGuestLookrecord = busGuestLookrecordService.findById(id);
        return ResultGenerator.genOkResult(busGuestLookrecord);
    }

    @PostMapping("/list")
    public Result list(@RequestBody Map<String,Object> map) {
    	PageHelper.startPage(Integer.valueOf( map.get("page").toString()), Integer.valueOf( map.get("size").toString()));
        List<BusGuestLookrecord> list = busGuestLookrecordService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
