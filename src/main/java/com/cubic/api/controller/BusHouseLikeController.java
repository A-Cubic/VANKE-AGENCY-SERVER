package com.cubic.api.controller;

import com.cubic.api.core.response.Result;
import com.cubic.api.core.response.ResultGenerator;
import com.cubic.api.model.BusHouseLike;
import com.cubic.api.service.BusHouseLikeService;
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
 * @date 2018/07/20
 */
@RestController
@RequestMapping("/vanke/house/like")
public class BusHouseLikeController {
    @Resource
    private BusHouseLikeService busHouseLikeService;
    /**
     * 创建关注
     * @param busHouseLike
     * 
     * */
    @PreAuthorize("hasAuthority('houselike:insert')")
    @PostMapping("/insert")
    public Result add(Principal user,@RequestBody BusHouseLike busHouseLike) {
    	busHouseLike.setUserName(user.getName());
    	busHouseLikeService.insertHouseLike(busHouseLike);
        return ResultGenerator.genOkResult("关注成功");
    }
    /**
     * 取消关注
     * @param busHouseLike
     * 
     * */
    @PreAuthorize("hasAuthority('houselike:delete')")
    @PostMapping("/delete")
    public Result delete(Principal user,@RequestBody BusHouseLike busHouseLike) {
    	busHouseLike.setUserName(user.getName());
    	busHouseLikeService.deleteHouseLike(busHouseLike);
        return ResultGenerator.genOkResult("取消关注");
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

    @PostMapping("/list")
    public Result list(Principal user,@RequestBody Map<String,Object> map) {
    	map.put("userName", user.getName());
        PageHelper.startPage(Integer.valueOf( map.get("page").toString()), Integer.valueOf( map.get("size").toString()));
        List<BusHouseLike> list = busHouseLikeService.listHouseLike(map);
        PageInfo<BusHouseLike> pageInfo = new PageInfo<BusHouseLike>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
