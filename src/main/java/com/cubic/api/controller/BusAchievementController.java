package com.cubic.api.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RestController;

import com.cubic.api.core.response.Result;
import com.cubic.api.core.response.ResultGenerator;
import com.cubic.api.model.BusAchievement;
import com.cubic.api.model.BusExamine;
import com.cubic.api.model.BusHouse;
import com.cubic.api.model.BusHouseTransaction;
import com.cubic.api.service.BusAchievementService;
import com.cubic.api.service.BusExamineService;
import com.cubic.api.service.BusHouseService;
import com.cubic.api.service.BusHouseTransactionService;
import com.cubic.api.service.MessageService;
import com.cubic.api.util.MessageConstant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author cubic
 * @date 2018/07/23
 */
@RestController
@RequestMapping("/vanke/achievement")
public class BusAchievementController {
    @Resource
    private BusAchievementService busAchievementService;
    @Resource
    private BusHouseTransactionService busHouseTransactionService;
    @Resource
    private BusHouseService busHouseService;
    @Resource
    private MessageService messageService;
    @Resource
    private BusExamineService busExamineService;
    /**
     * 成交业绩创建
     * @param List<BusAchievement>
     * */
    @PreAuthorize("hasAuthority('achievement:insert')")
    @PostMapping("/insert")
    public Result add(Principal user,@RequestBody List<BusAchievement> listbusAchievement) {
    	
    	//审核信息
    	BusExamine busExamine=new BusExamine();
    	busExamine.setTransactionId(listbusAchievement.get(0).getTransactionId());
    	String msgContent = MessageConstant.MESSAGE_AUDIT_ALLOT;
    	String url=MessageConstant.MESSAGE_GUEST_URL;
    	
		    	for(BusAchievement busAchievement:listbusAchievement){
		    		busAchievementService.insertExamineAchievement(busAchievement);    	
		    	}
		    	//发送消息
		    	messageService.sendMessage("1", msgContent, url, user.getName());
		    	busExamine.setType("10");
		    	busExamine.setUserName(user.getName());
		    	busExamineService.insertBusExamine(busExamine);
        return ResultGenerator.genOkResult("1");
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
	/**
	 * 查看分配业绩
	 * @param map
	 * */
    @PostMapping("/detailList")
    public Result detail(@RequestBody Map<String,Object> map) {
    	List<BusAchievement> busAchievementlist = busAchievementService.detailListAchievement(map);
        return ResultGenerator.genOkResult(busAchievementlist);
    }
    
/**
 * 点击编辑查询业绩
 * @param busAchievement
 * */
    @PostMapping("/getAchievement")
    public Result getAchievement(Principal user,@RequestBody BusAchievement busAchievement) {
    	BusHouseTransaction busHouseTransaction=null;
    	List<BusAchievement> list=new ArrayList<BusAchievement>();
    	if(busAchievement.getTransactionId()!=null && !"".equals(busAchievement.getTransactionId())){
    		Map<String,Object> map =new HashMap<String,Object>();
    		map.put("id", busAchievement.getTransactionId());
    		busHouseTransaction=busHouseTransactionService.detailTransaction(map);
    	}else {
    		  return ResultGenerator.genOkResult("0");   		
    	}
    	//合计金额
    	double price=Double.parseDouble(busHouseTransaction.getBuyIntermediaryPrice())+Double.parseDouble(busHouseTransaction.getBuyLoanPrice())+Double.parseDouble(busHouseTransaction.getSellIntermediaryPrice());
    	//百分之一百
    	double pro=1;
    	//获取房源信息
    	BusHouse busHouse =busHouseService.findById(busHouseTransaction.getHouseId());
    	if(busHouse.getCreateUserName()!=null && !"".equals(busHouse.getCreateUserName())){
    		BusAchievement busAchievementNew=new BusAchievement();
    		//录入人账号名
    		busAchievementNew.setUserName(busHouse.getCreateUserName());
    		//录入人真实姓名
    		busAchievementNew.setUserrelname(busHouse.getCreaterelName());
    		//房源id
    		busAchievementNew.setHouseId(busHouse.getId());
    		//成交id
    		busAchievementNew.setTransactionId(busHouseTransaction.getId());
    		//角色类型:录入人(1)
    		busAchievementNew.setRolenum("1");
    		//录入人占百分之十
    		double proCreate=0.1;
    		//业绩百分比
    		busAchievementNew.setProportion(String.valueOf(proCreate*100));
    		pro=pro-proCreate;
    		double priceCreate=price*proCreate;
    		price=price-priceCreate;
    		//录入人业绩金额
    		busAchievementNew.setPrice(String.valueOf(priceCreate));
    		list.add(busAchievementNew);
    	}
    	
    	if(busHouse.getRecordUserName()!=null && !"".equals(busHouse.getRecordUserName())){
    		BusAchievement busAchievementNew=new BusAchievement();
    		//维护人账号名
    		busAchievementNew.setUserName(busHouse.getRecordUserName());
    		//维护人真实姓名
    		busAchievementNew.setUserrelname(busHouse.getRecordrelName());
    		//房源id
    		busAchievementNew.setHouseId(busHouse.getId());
    		//成交id
    		busAchievementNew.setTransactionId(busHouseTransaction.getId());
    		//角色类型:维护人(2)
    		busAchievementNew.setRolenum("2");
    		//维护人占百分之十
    		double proRecord=0.1;
    		//业绩百分比
    		busAchievementNew.setProportion(String.valueOf(proRecord*100));
    		pro=pro-proRecord;
    		double priceRecord=price*proRecord;
    		price=price-priceRecord;
    		//维护人业绩金额
    		busAchievementNew.setPrice(String.valueOf(priceRecord));
    		list.add(busAchievementNew);
    	}
    	
    	if(busHouse.getKeyUserName()!=null && !"".equals(busHouse.getKeyUserName())){
    		BusAchievement busAchievementNew=new BusAchievement();
    		//钥匙人账号名
    		busAchievementNew.setUserName(busHouse.getKeyUserName());
    		//钥匙人真实姓名
    		busAchievementNew.setUserrelname(busHouse.getKeyrelName());
    		//房源id
    		busAchievementNew.setHouseId(busHouse.getId());
    		//成交id
    		busAchievementNew.setTransactionId(busHouseTransaction.getId());
    		//角色类型:钥匙人(3)
    		busAchievementNew.setRolenum("3");
    		//钥匙人占百分之5
    		double proKey=0.05;
    		//业绩百分比
    		busAchievementNew.setProportion(String.valueOf(proKey*100));
    		pro=pro-proKey;
    		double priceKey=price*proKey;
    		price=price-priceKey;
    		//钥匙人业绩金额
    		busAchievementNew.setPrice(String.valueOf(priceKey));
    		list.add(busAchievementNew);
    	}
    	
    	if(busHouse.getExplorationUserName()!=null && !"".equals(busHouse.getExplorationUserName())){
    		BusAchievement busAchievementNew=new BusAchievement();
    		//实勘人账号名
    		busAchievementNew.setUserName(busHouse.getExplorationUserName());
    		//实勘人真实姓名
    		busAchievementNew.setUserrelname(busHouse.getExplorationrelName());
    		//房源id
    		busAchievementNew.setHouseId(busHouse.getId());
    		//成交id
    		busAchievementNew.setTransactionId(busHouseTransaction.getId());
    		//角色类型:实勘人(4)
    		busAchievementNew.setRolenum("4");
    		//实勘人占百分之10
    		double proExploration=0.10;
    		//业绩百分比
    		busAchievementNew.setProportion(String.valueOf(proExploration*100));
    		pro=pro-proExploration;
    		double priceExploration=price*proExploration;
    		price=price-priceExploration;
    		//实勘人业绩金额
    		busAchievementNew.setPrice(String.valueOf(priceExploration));
    		list.add(busAchievementNew);
    	}
    	
    	//促成人
    		BusAchievement busAchievementNew=new BusAchievement();
    		//促成人账号名
    		busAchievementNew.setUserName(busHouseTransaction.getCreateUserName());
    		//促成人真实姓名
    		busAchievementNew.setUserrelname(busHouseTransaction.getCreaterelName());
    		//房源id
    		busAchievementNew.setHouseId(busHouse.getId());
    		//成交id
    		busAchievementNew.setTransactionId(busHouseTransaction.getId());
    		//角色类型:促成人(6)
    		busAchievementNew.setRolenum("6");
    		//促成人占剩下的全部
    		busAchievementNew.setProportion(String.valueOf(pro*100));
    		//促成人占剩下的全部
    		busAchievementNew.setPrice(String.valueOf(price));
    		list.add(busAchievementNew);
    	
        return ResultGenerator.genOkResult(list);
    }
    
	/**
	 * 查询我的业绩信息OR店长权限的人查询本店的业绩信息
	 * @param map
	 * @throws ParseException 
	 * */
    @PreAuthorize("hasAuthority('achievement:listMyAchievement')")
    @PostMapping("/listMyAchievement")
    public Result listMyAchievement(Principal user,@RequestBody Map<String,Object> map) throws ParseException {
    	PageHelper.startPage(Integer.valueOf( map.get("page").toString()), Integer.valueOf( map.get("size").toString()));
    	 if(user.toString().indexOf("ROLE_USER")!=-1){ //经济人权限只能看自己的业绩   		
    		map.put("userNameOne", user.getName());
    	}else if(user.toString().indexOf("ROLE_MANAGER")!=-1){//店长权限看本店的业绩		
    		map.put("userName", user.getName());
    	}else{//测试数据用
    		map.put("userNameOne", user.getName());
    	}
    	
        List<BusAchievement> list = busAchievementService.listMyAchievement(map);
        
        
        for(BusAchievement busAchievement:list){
        	
        	//转换时间格式
         	SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         	Date date = fmt.parse(busAchievement.getCreateTime());
    		String  sre= fmt.format(date);
    		busAchievement.setCreateTime(sre);
        	if(!"".equals(busAchievement.getRoleName())||null!=busAchievement.getRoleName()){
        		String rolenametext=busAchievement.getRoleName();
        		rolenametext=rolenametext.replaceAll("1","录入人");
        		rolenametext=rolenametext.replaceAll("2","维护人");
        		rolenametext=rolenametext.replaceAll("3","钥匙人");
        		rolenametext=rolenametext.replaceAll("4","实勘人");
        		rolenametext=rolenametext.replaceAll("5","独家人");
        		rolenametext=rolenametext.replaceAll("6","促成人");
        		rolenametext=rolenametext.replaceAll("7","促成合作人");
        		rolenametext = rolenametext.substring(0,rolenametext.length() - 1);
        		busAchievement.setRoleName(rolenametext);
        	}
        }
        PageInfo<BusAchievement> pageInfo = new PageInfo<BusAchievement>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
	/**
	 * 审核成交业绩
	 * @param map
	 * @throws ParseException 
	 * */
    @PreAuthorize("hasAuthority('achievement:examineAchievement')")
    @PostMapping("/examineAchievement")
    public Result examineAchievement(Principal user,@RequestBody Map<String,Object> map){
    	PageHelper.startPage(Integer.valueOf( map.get("page").toString()), Integer.valueOf( map.get("size").toString()));
    	List<BusAchievement> list = busAchievementService.examineAchievement(map);
    	  for(BusAchievement busAchievement:list){
          	
          	if(!"".equals(busAchievement.getRoleName())||null!=busAchievement.getRoleName()){
          		String rolenametext=busAchievement.getRoleName();
          		rolenametext=rolenametext.replaceAll("1","录入人");
          		rolenametext=rolenametext.replaceAll("2","维护人");
          		rolenametext=rolenametext.replaceAll("3","钥匙人");
          		rolenametext=rolenametext.replaceAll("4","实勘人");
          		rolenametext=rolenametext.replaceAll("5","独家人");
          		rolenametext=rolenametext.replaceAll("6","促成人");
          		rolenametext=rolenametext.replaceAll("7","促成合作人");
          		rolenametext = rolenametext.substring(0,rolenametext.length() - 1);
          		busAchievement.setRoleName(rolenametext);
          	}
          }
    	PageInfo<BusAchievement> pageInfo = new PageInfo<BusAchievement>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
    

}
