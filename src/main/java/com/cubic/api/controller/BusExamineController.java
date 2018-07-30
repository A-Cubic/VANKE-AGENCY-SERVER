package com.cubic.api.controller;

import com.cubic.api.core.response.Result;
import com.cubic.api.core.response.ResultGenerator;
import com.cubic.api.model.BusAchievement;
import com.cubic.api.model.BusExamine;
import com.cubic.api.model.BusGuest;
import com.cubic.api.model.BusHouse;
import com.cubic.api.service.BusAchievementService;
import com.cubic.api.service.BusExamineService;
import com.cubic.api.service.BusGuestService;
import com.cubic.api.service.BusHouseService;
import com.cubic.api.service.MessageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
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
    @Resource
    private MessageService messageService;
    @Resource
    private BusAchievementService busAchievementService;


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
    	String textExamine="";
    	//审核通过
    	if(busExamine.getResult().equals("1")){
    		//审核类型不是5:客源无效审核或9:取消无效客源审核
	    	if(!busExamineNew.getType().equals("5")||!busExamineNew.getType().equals("9")){
		    	BusHouse bushouse=new BusHouse();
		    	bushouse.setId(busExamineNew.getHouseId());
		    	if(busExamineNew.getType().equals("1")){//审核类型为特殊房源审核
		    		bushouse.setIsspecial("1");
		    		textExamine="特殊房源审核通过";
		    	}else if(busExamineNew.getType().equals("2")){  //审核类型为优质房源审核		
		    		bushouse.setIsfine("1");
		    		textExamine="优质房源审核通过";
		    	}else if(busExamineNew.getType().equals("3")){//审核类型为无效房源审核
		    		bushouse.setState("1");
		    		textExamine="无效房源审核通过";
		    	}else if(busExamineNew.getType().equals("4")){//审核类型为录入实勘图房源审核
		    		bushouse.setShiimg(busExamineNew.getShiimg());
		    		bushouse.setTingimg(busExamineNew.getTingimg());
		    		bushouse.setWeiimg(busExamineNew.getWeiimg());
		    		bushouse.setChuimg(busExamineNew.getChuimg());
		    		bushouse.setHuxingimg(busExamineNew.getHuxingimg());
		    		bushouse.setOtherimg(busExamineNew.getOtherimg());
		    		bushouse.setExplorationUserName(busExamineNew.getUserName());
		    		bushouse.setExplorationrelName(busExamineNew.getUserRelName());
		    		textExamine="录入实勘审核通过";
		    	}else if(busExamineNew.getType().equals("6")){//审核类型为取消特殊房源审核
		    		bushouse.setIsspecial("0");
		    		textExamine="取消特殊房源审核通过";
		    	}else if(busExamineNew.getType().equals("7")){//审核类型为取消优质房源审核
		    		bushouse.setIsfine("0");
		    		textExamine="优质房源审核审核通过";
		    	}else if(busExamineNew.getType().equals("8")){//审核类型为取消无效房源审核
		    		bushouse.setState("0");
		    		textExamine="取消无效房源审核通过";
		    	}
		    	
		    	busHouseService.update(bushouse);
	    	}else if(busExamineNew.getType().equals("5")){//客源无效审核
	    		BusGuest busGuest=new BusGuest();
	    		busGuest.setId(busExamineNew.getGuestId());
	    		busGuest.setIskey("1");
	    		busGuestService.update(busGuest);
	    		textExamine="无效审核通过";
	    	}else if(busExamineNew.getType().equals("9")){//取消客源无效审核
	    		BusGuest busGuest=new BusGuest();
	    		busGuest.setId(busExamineNew.getGuestId());
	    		busGuest.setIskey("0");
	    		busGuestService.update(busGuest);
	    		textExamine="取消客源无效审核通过";
	    	}else if (busExamineNew.getType().equals("10")){
	    		BusAchievement busAchievement =new BusAchievement();
	    		busAchievement.setTransactionId(busExamineNew.getTransactionId());
	    		busAchievementService.updateExamineType(busAchievement);
	    		textExamine="成交业绩分配审核通过";
	    	}
    	}else if(busExamine.getResult().equals("0")){//审核未通过
    		//未通过理由
    		textExamine=busExamine.getContent();
    		//判断不为客源类型的审核
        	if(!busExamineNew.getType().equals("5")||!busExamineNew.getType().equals("9")){
		    	BusHouse bushouse=new BusHouse();
		    	bushouse.setId(busExamineNew.getHouseId());
		    	if(busExamineNew.getType().equals("1")){//提交特殊房源审核未通过,状态从2:提交待审核变回0:不是特殊房源
		    		bushouse.setIsspecial("0");
		    		
		    	}else if(busExamineNew.getType().equals("2")){  //提交优质房源审核未通过,状态从2:提交待审核变回0:不是优质房源		
		    		bushouse.setIsfine("0");
		    		
		    	}else if(busExamineNew.getType().equals("3")){//提交无效房源审核未通过,状态从2:提交待审核变回0:不是无效房源
		    		bushouse.setState("0");
		    		
		    	}else if(busExamineNew.getType().equals("6")){//提交取消特殊房源审核未通过,状态从2:提交待审核变回1:特殊房源
		    		bushouse.setIsspecial("1");
		    	}else if(busExamineNew.getType().equals("7")){//提交取消优质房源审核未通过,状态从2:提交待审核变回1:优质房源
		    		bushouse.setIsfine("1");	    		
		    	}else if(busExamineNew.getType().equals("8")){//提交取消无效房源审核未通过,状态从2:提交待审核变回1:无效房源
		    		bushouse.setState("1");	    		
		    	}
		    	
		    	busHouseService.update(bushouse);
	    	}else if(busExamineNew.getType().equals("5")){
	    		BusGuest busGuest=new BusGuest();
	    		busGuest.setId(busExamineNew.getGuestId());
	    		busGuest.setIskey("0");
	    		busGuestService.update(busGuest);
	    	}else if(busExamineNew.getType().equals("9")){
	    		BusGuest busGuest=new BusGuest();
	    		busGuest.setId(busExamineNew.getGuestId());
	    		busGuest.setIskey("1");
	    		busGuestService.update(busGuest);
	    	}
    		
    	}
    	messageService.sendMessageResult(textExamine, "", user.getName(), busExamineNew.getUserName());
    	busExamine.setState("1");
    	busExamine.setUserName(user.getName());
    	busExamineService.updateResult(busExamine);
        return ResultGenerator.genOkResult("审核完成");
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
     * @throws ParseException 
     * 
     * */
    @PreAuthorize("hasAuthority('examine:list')")
    @PostMapping("/list")
    public Result list(Principal user,@RequestBody Map<String,Object> map) throws ParseException {
    	PageHelper.startPage(Integer.valueOf( map.get("page").toString()), Integer.valueOf( map.get("size").toString()));
    	map.put("username", user.getName());
    	if(user.toString().indexOf("ROLE_SEC")!=-1){//登录人是助理只能看实勘
    		map.put("role","3");
    	}
    	List<BusExamine> list = busExamineService.listBusExamine(map);
    	for(BusExamine busExamine:list){
    		
    		//转换时间格式(提交审核时间)
         	SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         	Date date = fmt.parse(busExamine.getCreateTime());
    		String  sre= fmt.format(date);
    		busExamine.setCreateTime(sre);
    		
    		if(busExamine.getExamineTime()!=null){
	    		//转换时间格式(审批时间)
	    	  	fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	         	date = fmt.parse(busExamine.getExamineTime());
	    		sre= fmt.format(date);
	    		busExamine.setExamineTime(sre);
    		}
    		
    		//图片显示
    	     if(null!=busExamine.getShiimg() && !"".equals(busExamine.getShiimg())){       	    	 
    	    	 busExamine.setShiImglist(Arrays.asList(busExamine.getShiimg().split(",")));
    	     }   	     
    	     if(null!=busExamine.getTingimg() && !"".equals(busExamine.getTingimg())){		        	    	 
               busExamine.setTingImglist(Arrays.asList(busExamine.getTingimg().split(",")));
           	     }
    	     if(null!=busExamine.getWeiimg() && !"".equals(busExamine.getWeiimg())){	    	 
    	    	 busExamine.setWeiImglist(Arrays.asList(busExamine.getWeiimg().split(",")));
    	     }
    	     if(null!=busExamine.getChuimg() && !"".equals(busExamine.getChuimg())){
    	    	 busExamine.setChuImglist(Arrays.asList(busExamine.getChuimg().split(",")));
    	     }
    		 if(null!=busExamine.getHuxingimg() && !"".equals(busExamine.getHuxingimg())){
    			 busExamine.setHuxingImglist(Arrays.asList(busExamine.getHuxingimg().split(",")));
    					     }
    		 if(null!=busExamine.getOtherimg() && !"".equals(busExamine.getOtherimg())){
    			 busExamine.setOtherImglist(Arrays.asList(busExamine.getOtherimg().split(",")));   		  	 
    		   }				 
    	
    		
    	}
    	
        PageInfo<BusExamine> pageInfo = new PageInfo<BusExamine>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
