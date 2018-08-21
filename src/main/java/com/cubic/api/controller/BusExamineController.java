package com.cubic.api.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cubic.api.core.response.Result;
import com.cubic.api.core.response.ResultGenerator;
import com.cubic.api.model.BusAchievement;
import com.cubic.api.model.BusExamine;
import com.cubic.api.model.BusGuest;
import com.cubic.api.model.BusHouse;
import com.cubic.api.model.BusHouseClicklog;
import com.cubic.api.model.BusHouseLookrecord;
import com.cubic.api.model.BusHouseRecord;
import com.cubic.api.model.BusHouseTransaction;
import com.cubic.api.service.BusAchievementService;
import com.cubic.api.service.BusExamineService;
import com.cubic.api.service.BusGuestLookrecordService;
import com.cubic.api.service.BusGuestRecordService;
import com.cubic.api.service.BusGuestService;
import com.cubic.api.service.BusHouseClicklogService;
import com.cubic.api.service.BusHouseLookrecordService;
import com.cubic.api.service.BusHouseRecordService;
import com.cubic.api.service.BusHouseService;
import com.cubic.api.service.BusHouseTransactionService;
import com.cubic.api.service.MessageService;
import com.cubic.api.util.MessageConstant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

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
    private BusHouseLookrecordService busHouseLookrecordService;
    @Resource
    private BusHouseRecordService busHouseRecordService;
    @Resource
    private BusHouseClicklogService busHouseClicklogService;
    @Resource
    private BusGuestService busGuestService;
    
    @Resource
    private BusGuestLookrecordService busGuestLookrecordService;
    
    @Resource
    private BusGuestRecordService busGuestRecordService;
    @Resource
    private MessageService messageService;
    @Resource
    private BusAchievementService busAchievementService;
    @Resource
    private BusHouseTransactionService busHouseTransactionService;


//    @PostMapping
//    public Result add(@RequestBody BusExamine busExamine) {
//    	busExamineService.save(busExamine);
//        return ResultGenerator.genOkResult();
//    }
//
//    @DeleteMapping("/{id}")
//    public Result delete(@PathVariable Long id) {
//    	busExamineService.deleteById(id);
//        return ResultGenerator.genOkResult();
//    }
    /**
     * 处理审批
     * @param busGuest
     * 
     * */
    @PreAuthorize("hasAuthority('examine:updateResult')")
    @PostMapping("/updateResult")
    public Result updateResult(Principal user,@RequestBody BusExamine busExamine) {
    	//查询审核信息
    	BusExamine busExamineNew=busExamineService.findById(busExamine.getId());
    	//判断是否有人审核过了(0:未审核,1:已审核)
    	if(busExamineNew.getState().equals("1")){
    		  return ResultGenerator.genOkResult("已经有人审核过了");
    	}
    	String textExamine="";
    	String url="";
    	//审核通过
    	if(busExamine.getResult().equals("1")){
    		//审核类型不是5:客源无效审核或9:取消无效客源审核
	    	if(!"5".equals(busExamineNew.getType()) && !"9".equals(busExamineNew.getType())&&!"10".equals(busExamineNew.getType())){
		    	BusHouse bushouse=new BusHouse();
		    	bushouse.setId(busExamineNew.getHouseId());
		    	if(busExamineNew.getType().equals("1")){//审核类型为特殊房源审核
		    		bushouse.setIsspecial("1");
		    		textExamine=MessageConstant.MESSAGE_SUCCESS_HOUSE_SPECIAL;
		    		busHouseService.update(bushouse);
		    	}else if(busExamineNew.getType().equals("2")){  //审核类型为优质房源审核		
		    		bushouse.setIsfine("1");
		    		textExamine=MessageConstant.MESSAGE_SUCCESS_HOUSE_GOOD;
		    		busHouseService.update(bushouse);
		    	}else if(busExamineNew.getType().equals("3")){//审核类型为无效房源审核
		    		//情况相关信息并设置为无效房源
		    		//清除房源的带看记录
		    		busHouseLookrecordService.deleteLookrecord(bushouse.getId().toString());
		    		//清除房源跟进记录
		    		busHouseRecordService.deleteRecord(bushouse.getId().toString());;
		    		//清空点击查看的记录
		    		busHouseClicklogService.deleteClick(bushouse.getId().toString());
		    		//清空房源待审核信息
		    		busExamineService.deleteHouseState(bushouse.getId().toString());
		    		//把房源下网
		    		busHouseService.houseStateDown(bushouse.getId().toString());		    		
		    		textExamine=MessageConstant.MESSAGE_SUCCESS_HOUSE_INVALID;
		    	}else if(busExamineNew.getType().equals("4")){//审核类型为录入实勘图房源审核
		    		bushouse.setShiimg(busExamineNew.getShiimg());
		    		bushouse.setTingimg(busExamineNew.getTingimg());
		    		bushouse.setWeiimg(busExamineNew.getWeiimg());
		    		bushouse.setChuimg(busExamineNew.getChuimg());
		    		bushouse.setHuxingimg(busExamineNew.getHuxingimg());
		    		bushouse.setOtherimg(busExamineNew.getOtherimg());
		    		bushouse.setExplorationUserName(busExamineNew.getUserName());
		    		bushouse.setExplorationrelName(busExamineNew.getUserRelName());
		    		if(!"".equals(busExamineNew.getShiimg())&&null!=busExamineNew.getShiimg()){//缩略图
		    			bushouse.setTitleimg(Arrays.asList(busExamineNew.getShiimg().split(",")).get(0));
		    		}
		    		
		    		textExamine=MessageConstant.MESSAGE_SUCCESS_HOUSE_REALIMG;
		    		busHouseService.update(bushouse);
		    	}else if(busExamineNew.getType().equals("6")){//审核类型为取消特殊房源审核
		    		bushouse.setIsspecial("0");
		    		textExamine=MessageConstant.MESSAGE_SUCCESS_HOUSE_NOSPECIAL;
		    		busHouseService.update(bushouse);
		    	}else if(busExamineNew.getType().equals("7")){//审核类型为取消优质房源审核
		    		bushouse.setIsfine("0");
		    		textExamine=MessageConstant.MESSAGE_SUCCESS_HOUSE_NOGOOD;
		    		busHouseService.update(bushouse);
		    	}
		    	
//		    	else if(busExamineNew.getType().equals("8")){//审核类型为取消无效房源审核
//		    		bushouse.setState("0");
//		    		textExamine=MessageConstant.MESSAGE_SUCCESS_HOUSE_NOINVALID;
//		    	}
		    	url=MessageConstant.MESSAGE_HOUSE_URL+bushouse.getId();
		    
	    	}else if(busExamineNew.getType().equals("5")){//客源无效审核
	    		BusGuest busGuest=new BusGuest();
	    		busGuest.setId(busExamineNew.getGuestId());
	    		//清空带看记录
	    		busGuestLookrecordService.deleteBy("guestId", busExamineNew.getGuestId());
	    		//清空跟进记录
	    		busGuestRecordService.deleteBy("guestId", busExamineNew.getGuestId());
	    		//清空所有的待审核信息
	    		busExamineService.deleteGuestState(busExamineNew.getGuestId().toString());
	    		//设置无效客源下网
	    		busGuestService.updateGuestIsKeyDown(busGuest);
	    		textExamine=MessageConstant.MESSAGE_SUCCESS_GUEST_INVALID;
	    		url=MessageConstant.MESSAGE_GUEST_URL+busGuest.getId();
	    	}
	    	//else if(busExamineNew.getType().equals("9")){//取消客源无效审核
//	    		BusGuest busGuest=new BusGuest();
//	    		busGuest.setId(busExamineNew.getGuestId());
//	    		busGuest.setIskey("0");
//	    		busGuestService.update(busGuest);
//	    		textExamine=MessageConstant.MESSAGE_SUCCESS_GUEST_NOINVALID;
//	    		url=MessageConstant.MESSAGE_GUEST_URL+busGuest.getId();
//	    	}
	    	else if (busExamineNew.getType().equals("10")){
	    		//通过业绩
	    		BusAchievement busAchievement =new BusAchievement();
	    		busAchievement.setTransactionId(busExamineNew.getTransactionId());
	    		busAchievement.setExamineType("1");//审核状态设置为1:已通过
	    		busAchievementService.updateExamineType(busAchievement);
	    		//成交状态设置为3:完成成交
	    		BusHouseTransaction busHouseTransaction = new BusHouseTransaction();
	    		busHouseTransaction.setId(busExamineNew.getTransactionId());
	    		busHouseTransaction.setState("3");
	    		busHouseTransactionService.update(busHouseTransaction);
	    		textExamine=MessageConstant.MESSAGE_SUCCESS_AUDIT_ALLOT;
	    		url=MessageConstant.MESSAGE_CLINCHDEAL_URL;
	    	}
    	}else if(busExamine.getResult().equals("0")){//审核未通过
    		//未通过理由
    		String noStr="";
    		//判断不为客源类型的审核
    		if(!"5".equals(busExamineNew.getType()) && !"9".equals(busExamineNew.getType())&&!"10".equals(busExamineNew.getType())){
		    	BusHouse bushouse=new BusHouse();
		    	bushouse.setId(busExamineNew.getHouseId());
		    	 
		    		if(busExamineNew.getType().equals("1")){//提交特殊房源审核未通过,状态从2:提交待审核变回0:不是特殊房源
			    		bushouse.setIsspecial("0");
			    		noStr=MessageConstant.MESSAGE_FAIL_HOUSE_SPECIAL;
			    	}else if(busExamineNew.getType().equals("2")){  //提交优质房源审核未通过,状态从2:提交待审核变回0:不是优质房源		
			    		bushouse.setIsfine("0");
			    		noStr=MessageConstant.MESSAGE_FAIL_HOUSE_GOOD;
			    	}else if(busExamineNew.getType().equals("3")){//提交无效房源审核未通过,状态从2:提交待审核变回0:不是无效房源
			    		bushouse.setState("0");
			    		noStr=MessageConstant.MESSAGE_FAIL_HOUSE_INVALID;
			    	}else if(busExamineNew.getType().equals("4")) {
			    		bushouse.setExplorationUserName("");
			    		noStr=MessageConstant.MESSAGE_FAIL_HOUSE_REALIMG;
			    	}else if(busExamineNew.getType().equals("6")){//提交取消特殊房源审核未通过,状态从2:提交待审核变回1:特殊房源
			    		bushouse.setIsspecial("1");
			    		noStr=MessageConstant.MESSAGE_FAIL_HOUSE_NOSPECIAL;
			    	}else if(busExamineNew.getType().equals("7")){//提交取消优质房源审核未通过,状态从2:提交待审核变回1:优质房源
			    		bushouse.setIsfine("1");
			    		noStr=MessageConstant.MESSAGE_FAIL_HOUSE_NOGOOD;
			    	}else if(busExamineNew.getType().equals("8")){//提交取消无效房源审核未通过,状态从2:提交待审核变回1:无效房源
			    		bushouse.setState("1");	 
			    		noStr=MessageConstant.MESSAGE_FAIL_HOUSE_NOINVALID;
			    	}
		    		busHouseService.update(bushouse);
		    	
		    	
		    	url=MessageConstant.MESSAGE_HOUSE_URL+bushouse.getId();
	    	}else if(busExamineNew.getType().equals("5")){
	    		noStr=MessageConstant.MESSAGE_FAIL_GUEST_INVALID;
	    		BusGuest busGuest=new BusGuest();
	    		busGuest.setId(busExamineNew.getGuestId());
	    		busGuest.setIskey("0");
	    		busGuestService.update(busGuest);
	    		url=MessageConstant.MESSAGE_GUEST_URL+busGuest.getId();
	    	}else if(busExamineNew.getType().equals("9")){
	    		noStr=MessageConstant.MESSAGE_FAIL_GUEST_NOINVALID;
	    		BusGuest busGuest=new BusGuest();
	    		busGuest.setId(busExamineNew.getGuestId());
	    		busGuest.setIskey("1");
	    		busGuestService.update(busGuest);
	    		url=MessageConstant.MESSAGE_GUEST_URL+busGuest.getId();
	    	}else if(busExamineNew.getType().equals("10")){
	    		noStr=MessageConstant.MESSAGE_FAIL_AUDIT_ALLOT;
	    		//删除成交未通过的业绩
	    		busAchievementService.deleteTransactionAchievement(busExamineNew.getTransactionId());
	    		
	    		//成交状态设置为1:添加完成,分配业绩
	    		BusHouseTransaction busHouseTransaction = new BusHouseTransaction();
	    		busHouseTransaction.setId(busExamineNew.getTransactionId());
	    		busHouseTransaction.setState("1");
	    		busHouseTransactionService.update(busHouseTransaction);
	    		url=MessageConstant.MESSAGE_CLINCHDEAL_URL;
	    	}
        	textExamine=noStr+busExamine.getContent();
    	}
    	//发送消息
    	messageService.sendMessageResult(textExamine, url, user.getName(), busExamineNew.getUserName());
    	busExamine.setState("1");
    	busExamine.setUserName(user.getName());
    	busExamineService.updateResult(busExamine);
        return ResultGenerator.genOkResult("审核完成");
    }

    /**
     * 审核详情查询
     * @param map
     * 
     * */
    @PreAuthorize("hasAuthority('examine:detail')")
    @PostMapping("/detail")
    public Result detail(@RequestBody Map<String,Object> map) {
    	//详细信息
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
