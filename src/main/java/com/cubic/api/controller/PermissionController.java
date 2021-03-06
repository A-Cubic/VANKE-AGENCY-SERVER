package com.cubic.api.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.cubic.api.core.response.Result;
import com.cubic.api.core.response.ResultGenerator;
import com.cubic.api.service.PermissionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author fei.yu
 * @date 2018/06/09
 */
@RestController
@RequestMapping("/vanke/permission")
public class PermissionController {
    @Resource
    private PermissionService permissionService;

    @PreAuthorize("hasAuthority('role:list')")
    @GetMapping
    public Result listResourcePermission(@RequestParam(defaultValue = "0") final Integer page,
                                         @RequestParam(defaultValue = "0") final Integer size) {
        PageHelper.startPage(page, size);
        final List<JSONObject> list = this.permissionService.findAllResourcePermission();
        //noinspection unchecked
        final PageInfo<JSONObject> pageInfo = new PageInfo<JSONObject>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
