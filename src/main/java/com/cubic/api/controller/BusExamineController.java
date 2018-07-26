package com.cubic.api.controller;

import com.cubic.api.core.response.Result;
import com.cubic.api.core.response.ResultGenerator;
import com.cubic.api.model.BusExamine;
import com.cubic.api.model.BusGuest;
import com.cubic.api.model.BusHouse;
import com.cubic.api.service.BusExamineService;
import com.cubic.api.service.BusGuestService;
import com.cubic.api.service.BusHouseService;
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
    @Resource
    private BusHouseService busHouseService;
    @Resource
    private BusGuestService busGuestService;

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
    	BusExamine busExamineNew=busExamineService.findById(busExamine.getId());
    	if(busExamineNew.getState().equals("2")){
    		  return ResultGenerator.genOkResult("已经有人审核过了");
    	}
    	if(busExamine.getResult().equals("1")){
	    	if(!busExamineNew.getType().equals("5")){
		    	BusHouse bushouse=new BusHouse();
		    	bushouse.setId(busExamineNew.getHouseId());
		    	if(busExamineNew.getType().equals("1")){
		    		bushouse.setIsspecial("1");
		    	}else if(busExamineNew.getType().equals("2")){  		
		    		bushouse.setIsfine("1");
		    	}else if(busExamineNew.getType().equals("3")){
		    		bushouse.setState("1");
		    	}else if(busExamineNew.getType().equals("4")){
		    		bushouse.setShiimg(busExamineNew.getShiimg());
		    		bushouse.setTingimg(busExamineNew.getTingimg());
		    		bushouse.setWeiimg(busExamineNew.getWeiimg());
		    		bushouse.setChuimg(busExamineNew.getChuimg());
		    		bushouse.setHuxingimg(busExamineNew.getHuxingimg());
		    		bushouse.setOtherimg(busExamineNew.getOtherimg());
		    		bushouse.setExplorationUserName(busExamineNew.getUserName());
		    		bushouse.setExplorationrelName(busExamineNew.getUserRelName());
		    	}else if(busExamineNew.getType().equals("6")){
		    		bushouse.setIsspecial("0");
		    	}else if(busExamineNew.getType().equals("7")){
		    		bushouse.setIsfine("0");	    		
		    	}else if(busExamineNew.getType().equals("8")){
		    		bushouse.setState("0");	    		
		    	}
		    	
		    	busHouseService.update(bushouse);
	    	}else if(busExamineNew.getType().equals("5")){
	    		BusGuest busGuest=new BusGuest();
	    		busGuest.setId(busExamineNew.getGuestId());
	    		busGuest.setIskey("1");
	    		busGuestService.update(busGuest);
	    	}else if(busExamineNew.getType().equals("9")){
	    		BusGuest busGuest=new BusGuest();
	    		busGuest.setId(busExamineNew.getGuestId());
	    		busGuest.setIskey("0");
	    		busGuestService.update(busGuest);
	    	}
    	}
    	busExamine.setState("1");
    	busExamine.setUserName(user.getName());
    	busExamineService.updateResult(busExamine);
        return ResultGenerator.genOkResult("审核成功");
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
    /**
     * 审核详情查询
     * @param map
     * 
     * */
    @PreAuthorize("hasAuthority('examine:detail')")
    @PostMapping("/detail")
    public Result detail(@RequestBody Map<String,Object> map) {
    	BusExamine busExamine = busExamineService.findById(Long.valueOf( map.get("id").toString()));
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
    	map.put("username", user.getName());
    	if(user.toString().indexOf("ROLE_SEC")!=-1){//登录人是助理只能看实勘
    		map.put("role","3");
    	}
    	List<BusExamine> list = busExamineService.listBusExamine(map);
    	
    	
        PageInfo<BusExamine> pageInfo = new PageInfo<BusExamine>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
