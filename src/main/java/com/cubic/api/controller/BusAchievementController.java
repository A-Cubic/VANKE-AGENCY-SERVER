package com.cubic.api.controller;

import com.cubic.api.core.response.Result;
import com.cubic.api.core.response.ResultGenerator;
import com.cubic.api.model.BusAchievement;
import com.cubic.api.service.BusAchievementService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 * @author cubic
 * @date 2018/07/23
 */
@RestController
@RequestMapping("/vanke/achievement")
public class BusAchievementController {
    @Resource
    private BusAchievementService busAchievementService;

    @PostMapping
    public Result add(@RequestBody BusAchievement busAchievement) {
busAchievementService.save(busAchievement);
        return ResultGenerator.genOkResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
busAchievementService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PutMapping
    public Result update(@RequestBody BusAchievement busAchievement) {
busAchievementService.update(busAchievement);
        return ResultGenerator.genOkResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
    	BusAchievement busAchievement = busAchievementService.findById(id);
        return ResultGenerator.genOkResult(busAchievement);
    }
    /**
     * 查询自己的业绩
     * */
    @PreAuthorize("hasAuthority('achievement:listMyAchievement')")
    @PostMapping("/listMyAchievement")
    public Result listMyAchievement(Principal user,@RequestBody Map<String,Object> map) {
    	PageHelper.startPage(Integer.valueOf( map.get("page").toString()), Integer.valueOf( map.get("size").toString()));
    	map.put("userName", user.getName());
        List<BusAchievement> list = busAchievementService.listMyAchievement(map);
        PageInfo<BusAchievement> pageInfo = new PageInfo<BusAchievement>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
    
    /**
     * 查询排行榜
     * */
    @PreAuthorize("hasAuthority('achievement:listAchievement')")
    @PostMapping("/listAchievement")
    public Result listAchievement(Principal user,@RequestBody Map<String,Object> map) {
    	PageHelper.startPage(Integer.valueOf( map.get("page").toString()), Integer.valueOf( map.get("size").toString()));
        List<BusAchievement> list = busAchievementService.listAchievement(map);
        PageInfo<BusAchievement> pageInfo = new PageInfo<BusAchievement>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}