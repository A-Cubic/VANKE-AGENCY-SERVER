package com.cubic.api.controller;

import java.security.Principal;
import java.util.List;

import javax.annotation.Resource;

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
import com.cubic.api.model.BusHouse;
import com.cubic.api.service.BusHouseService;
import com.cubic.api.util.NumberUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author cubic
 * @date 2018/07/12
 */
@RestController
@RequestMapping("/bus/house")
public class BusHouseController {
    @Resource
    private BusHouseService busHouseService;

   /**
    * 创建房源信息
    * @param user, busHouse
    * 
    * */
    @PostMapping("/save")
    public Result add(Principal user,@RequestBody BusHouse busHouse) {
    	//创建人账号名
    	busHouse.setCreateUserName(user.getName());
    	//维护人账号名
    	busHouse.setRecordUserName(user.getName());
    	busHouseService.save(busHouse);
    	//根据id得到房源编号并更新
    	String num = NumberUtil.geoEquipmentNo("H",busHouse.getId());
    	BusHouse busHouseNew=new BusHouse();
    	busHouseNew.setId(busHouse.getId());
    	busHouseNew.setNumber(num);
    	busHouseService.update(busHouseNew);
    	
        return ResultGenerator.genOkResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
    	busHouseService.deleteById(id);
        return ResultGenerator.genOkResult();
    }
    
    /**
     * 改变房源状态
     * @param busHouse
     * 状态:(0:普通,1:特殊,2:无效,3:已售出/已租出)
     * */
    @PutMapping("/updateState")
    public Result updateState(@RequestBody BusHouse busHouse) {
    	BusHouse busHouseNew=new BusHouse();
    	if(null != busHouse){
    		if(busHouse.getId() != null && busHouse.getState() != null){
    			busHouseNew.setId(busHouse.getId());
    			busHouseNew.setState(busHouse.getState());
    			busHouseService.update(busHouseNew);
    		}
    	}
        return ResultGenerator.genOkResult();
    }
    
    /**
     * 转赠房源维护人
     * @param busHouse
     * 
     * */
    @PutMapping("/updateRecordUser")
    public Result updateRecordUser(@RequestBody BusHouse busHouse) {
    	BusHouse busHouseNew=new BusHouse();
    	if(null != busHouse){
    		if(busHouse.getId() != null && busHouse.getRecordUserName() != null){
    			busHouseNew.setId(busHouse.getId());
    			busHouseNew.setRecordUserName(busHouse.getRecordUserName());
    			busHouseService.update(busHouseNew);
    		}
    	}
        return ResultGenerator.genOkResult();
    }
    /**
     * 修改钥匙状态
     * @param busHouse
     * 状态:(0:不在维护人手里,1:在维护人手里)
     * */
    @PutMapping("/updateIskey")
    public Result updateIsKey(@RequestBody BusHouse busHouse) {
    	BusHouse busHouseNew=new BusHouse();
    	if(null != busHouse){
    		if(busHouse.getId() != null && busHouse.getIskey() != null){
    			busHouseNew.setId(busHouse.getId());
    			busHouseNew.setIskey(busHouse.getIskey());
    			busHouseService.update(busHouseNew);
    		}
    	}
        return ResultGenerator.genOkResult();
    }
    /**
     * 修改房源等级
     * @param busHouse
     * 
     * */
    @PutMapping("/updateGrade")
    public Result updateGrade(@RequestBody BusHouse busHouse) {
    	BusHouse busHouseNew=new BusHouse();
    	if(null != busHouse){
    		if(busHouse.getId() != null && busHouse.getGrade() != null){
    			busHouseNew.setId(busHouse.getId());
    			busHouseNew.setGrade(busHouse.getGrade());
    			busHouseService.update(busHouseNew);
    		}
    	}
        return ResultGenerator.genOkResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
    	BusHouse busHouse = busHouseService.findById(id);
        return ResultGenerator.genOkResult(busHouse);
    }

    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<BusHouse> list = busHouseService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
