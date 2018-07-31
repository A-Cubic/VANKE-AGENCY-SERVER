package com.cubic.api.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
import com.cubic.api.model.BusHouseClicklog;
import com.cubic.api.service.BusHouseClicklogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

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
	/**
	 * 记录log
	 * */
    @PostMapping("/list")
    public Result list(Principal user,@RequestBody Map<String,Object> map) {
    	PageHelper.startPage(Integer.valueOf( map.get("page").toString()), Integer.valueOf( map.get("size").toString()));
        List<BusHouseClicklog> list = busHouseClicklogService.listClickLog(map);
        PageInfo<BusHouseClicklog> pageInfo = new PageInfo<BusHouseClicklog>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
