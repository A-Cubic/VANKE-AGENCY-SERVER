package com.cubic.api.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cubic.api.core.response.Result;
import com.cubic.api.core.response.ResultGenerator;
import com.cubic.api.model.Role;
import com.cubic.api.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author fei.yu
 * @date 2018/06/09
 */
@RestController
@RequestMapping("/vanke/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    @PreAuthorize("hasAuthority('role:add')")
    @PostMapping
    public Result add(@RequestBody final Role role) {
        this.roleService.save(role);
        return ResultGenerator.genOkResult();
    }

    @PreAuthorize("hasAuthority('role:delete')")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable final Long id) {
        this.roleService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PreAuthorize("hasAuthority('role:update')")
    @PutMapping
    public Result update(@RequestBody final Role role) {
        this.roleService.update(role);
        return ResultGenerator.genOkResult();
    }

    @PreAuthorize("hasAuthority('role:list')")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") final Integer page,
                       @RequestParam(defaultValue = "0") final Integer size) {
        PageHelper.startPage(page, size);
        final List<com.cubic.api.model.Resource> list = this.roleService.findAllRoleWithPermission();
        //noinspection unchecked
        final PageInfo<com.cubic.api.model.Resource> pageInfo = new PageInfo<com.cubic.api.model.Resource>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
    
    /**
     * 查询角色列表
     * */
    @PreAuthorize("hasAuthority('role:listDesc')")
    @PostMapping("/listDesc")
    public Result listDesc(Principal user) {
	    Map<String,Object> param=new HashMap<String,Object>();
	    param.put("userName", user.getName());
	   	 if(user.toString().indexOf("ROLE_ADMIN")!=-1){ //经理
	   		param.put("role", "1");
	   	}else if(user.toString().indexOf("ROLE_MANAGER")!=-1){//店长/	
	   		param.put("role", "4");
	   	}else if(user.toString().indexOf("ROLE_SEC")!=-1){//助理
	   		param.put("role", "3");
	   	}else {
	   	  return ResultGenerator.genOkResult(this.roleService.findAll());
	   	}
        final List<Role> list = this.roleService.listDesc(param);

      
        return ResultGenerator.genOkResult(list);
    }
}
