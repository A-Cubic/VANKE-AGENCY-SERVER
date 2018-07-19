package com.cubic.api.controller;

import com.cubic.api.core.response.Result;
import com.cubic.api.core.response.ResultGenerator;
import com.cubic.api.model.BusExamine;
import com.cubic.api.service.BusExamineService;
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
 * @date 2018/07/18
 */
@RestController
@RequestMapping("/vanke/examine")
public class BusExamineController {
    @Resource
    private BusExamineService busExamineService;

    @PostMapping
    public Result add(@RequestBody BusExamine busExamine) {
    	busExamineService.save(busExamine);
        return ResultGenerator.genOkResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
    	busExamineService.deleteById(id);
        return ResultGenerator.genOkResult();
    }
    /**
     * 处理审批
     * @param busGuest
     * 
     * */
    @PreAuthorize("hasAuthority('examine:updateResult')")
    @PostMapping("/updateResult")
    public Result updateResult(Principal user,@RequestBody BusExamine busExamine) {
    	busExamine.setState("2");
    	busExamine.setUserName(user.getName());
    	busExamineService.updateResult(busExamine);
        return ResultGenerator.genOkResult();
    }
    /**
     * 开始审核(审核状态设置为审核中:1)
     * @param busGuest
     * 
     * */
    @PreAuthorize("hasAuthority('examine:updateState')")
    @PostMapping("/updateState")
    public Result updateState(Principal user,@RequestBody BusExamine busExamine) {
    	busExamine.setState("1");
    	busExamine.setUserName(user.getName());
    	busExamineService.updateResult(busExamine);
        return ResultGenerator.genOkResult();
    }
    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
    	BusExamine busExamine = busExamineService.findById(id);
        return ResultGenerator.genOkResult(busExamine);
    }
    /**
     * 条件查询
     * @param map
     * 
     * */
    @PreAuthorize("hasAuthority('examine:list')")
    @PostMapping("/list")
    public Result list(Principal user,@RequestBody Map<String,Object> map) {
    	PageHelper.startPage(Integer.valueOf( map.get("page").toString()), Integer.valueOf( map.get("size").toString()));
        List<BusExamine> list = busExamineService.listBusExamine(map);
        PageInfo<BusExamine> pageInfo = new PageInfo<BusExamine>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
