package com.cubic.api.controller;

import com.cubic.api.core.response.Result;
import com.cubic.api.core.response.ResultGenerator;
import com.cubic.api.model.BusHouseLike;
import com.cubic.api.service.BusHouseLikeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author cubic
 * @date 2018/07/20
 */
@RestController
@RequestMapping("/vanke/house/like")
public class BusHouseLikeController {
    @Resource
    private BusHouseLikeService busHouseLikeService;

    @PostMapping
    public Result add(@RequestBody BusHouseLike busHouseLike) {
    	busHouseLikeService.insertHouseLike(busHouseLike);
        return ResultGenerator.genOkResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
    	busHouseLikeService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PutMapping
    public Result update(@RequestBody BusHouseLike busHouseLike) {
    	busHouseLikeService.update(busHouseLike);
        return ResultGenerator.genOkResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
    	BusHouseLike busHouseLike = busHouseLikeService.findById(id);
        return ResultGenerator.genOkResult(busHouseLike);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<BusHouseLike> list = busHouseLikeService.findAll();
        PageInfo<BusHouseLike> pageInfo = new PageInfo<BusHouseLike>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
