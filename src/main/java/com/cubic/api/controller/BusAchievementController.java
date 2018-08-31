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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cubic.api.core.response.Result;
import com.cubic.api.core.response.ResultGenerator;
import com.cubic.api.model.BusAchievement;
import com.cubic.api.model.BusExamine;
import com.cubic.api.model.BusHouse;
import com.cubic.api.model.BusHouseTransaction;
import com.cubic.api.model.Store;
import com.cubic.api.service.BusAchievementService;
import com.cubic.api.service.BusExamineService;
import com.cubic.api.service.BusHouseService;
import com.cubic.api.service.BusHouseTransactionService;
import com.cubic.api.service.MessageService;
import com.cubic.api.service.StoreService;
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
	@Resource
	private StoreService storeService;
    /**
     * 成交业绩创建
     * @param List<BusAchievement>
     * */
    @PreAuthorize("hasAuthority('achievement:insert')")
    @PostMapping("/insert")
    public Result add(Principal user,@RequestBody List<BusAchievement> listbusAchievement) {
    	
    	//审核信息
    	BusExamine busExamine=new BusExamine();
    	if(listbusAchievement!=null&&listbusAchievement.size()!=0){
    		Long id=null;
    		//验证成交是否为空
    		if(listbusAchievement.get(0).getTransactionId()!=null){
    			id=listbusAchievement.get(0).getTransactionId();
    			
    		}else {
    			return ResultGenerator.genOkResult("0");
    			
    		}
    		//审核的成交id
    		busExamine.setTransactionId(id);
    		//消息
        	String msgContent = MessageConstant.MESSAGE_AUDIT_ALLOT;
        	String url=MessageConstant.MESSAGE_AUDIT_URL;
        	        //房源id
        			Long houseId=null;
        			//成交id
        			Long transactionId=null;
    		    	for(BusAchievement busAchievement:listbusAchievement){
    		    		
    		    		//判断房源id和成交id不为空
    		    		if(busAchievement.getHouseId()!=null&&busAchievement.getTransactionId()!=null){  		    			
    		    			houseId=busAchievement.getHouseId();
    		    			transactionId=busAchievement.getTransactionId();
    		    		}
    		    		//角色为7:促成合作人的情况给houseid和transactionid赋值
    		    		if(busAchievement.getRolenum()!=null &&"7".equals( busAchievement.getRolenum())){
    		    			busAchievement.setHouseId(houseId);
    		    			busAchievement.setTransactionId(transactionId);
    		    		}
    		    		busAchievementService.insertExamineAchievement(busAchievement);    	
    		    	}
    		    	//修改成交状态为待审核
    		    	BusHouseTransaction busHouseTransaction=new BusHouseTransaction();
    		    	busHouseTransaction.setId(id);
    		    	busHouseTransaction.setState("2");
    		    	busHouseTransactionService.update(busHouseTransaction);
    		    	//发送消息
    		    	messageService.sendMessage("1", msgContent, url, user.getName());
    		    	busExamine.setType("10");
    		    	busExamine.setUserName(user.getName());
    		    	busExamineService.insertBusExamine(busExamine);
            return ResultGenerator.genOkResult("1");
    		
    	}else{
    		
    		return ResultGenerator.genOkResult("0");
    	}
    	
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody Map<String,Object> map) {
    	//busAchievementService.deleteTransactionAchievement(Long.valueOf(map.get("id").toString()));
        return ResultGenerator.genOkResult();
    }

//    @PutMapping
//    public Result update(@RequestBody BusAchievement busAchievement) {
//    	busAchievementService.update(busAchievement);
//        return ResultGenerator.genOkResult();
//    }
	/**
	 * 查看分配业绩
	 * @param map
	 * @throws ParseException 
	 * */
    @PreAuthorize("hasAuthority('achievement:detailList')")
    @PostMapping("/detailList")
    public Result detail(@RequestBody Map<String,Object> map) throws ParseException {
    	List<BusAchievement> busAchievementlist = busAchievementService.detailListAchievement(map);
    	  for(BusAchievement busAchievement:busAchievementlist){
    		  //占比角色
            	if(!"".equals(busAchievement.getRolenum())||null!=busAchievement.getRolenum()){
            		String rolenametext=busAchievement.getRolenum();
            		rolenametext=rolenametext.replaceAll("1","录入人");
            		rolenametext=rolenametext.replaceAll("2","维护人");
            		rolenametext=rolenametext.replaceAll("3","钥匙人");
            		rolenametext=rolenametext.replaceAll("4","实勘人");
            		rolenametext=rolenametext.replaceAll("5","独家人");
            		rolenametext=rolenametext.replaceAll("6","促成人");
            		rolenametext=rolenametext.replaceAll("7","促成合作人");
            		
            		busAchievement.setRoleName(rolenametext);
            	}
            	
            	
            }
        return ResultGenerator.genOkResult(busAchievementlist);
    }
    
	/**
	 * 点击编辑查询业绩
	 * @param busAchievement
	 * */
    @PreAuthorize("hasAuthority('achievement:getAchievement')")
    @PostMapping("/getAchievement")
    public Result getAchievement(Principal user,@RequestBody BusAchievement busAchievement) {
    	BusHouseTransaction busHouseTransaction=null;
    	List<BusAchievement> list=new ArrayList<BusAchievement>();
    	//验证成交id是否为空
    	if(busAchievement.getTransactionId() != null){
    		Map<String,Object> map =new HashMap<String,Object>();
    		map.put("id", busAchievement.getTransactionId());
    		//按照成交id查询成交详情
    		busHouseTransaction=busHouseTransactionService.detailTransaction(map);
    	}else {
    		  return ResultGenerator.genOkResult("0");   		
    	}
    	//合计金额
    	double price=Double.parseDouble(busHouseTransaction.getBuyIntermediaryPrice())+Double.parseDouble(busHouseTransaction.getBuyLoanPrice())+Double.parseDouble(busHouseTransaction.getSellIntermediaryPrice());
    	//百分之一百
    	double pro=1;
    	double priceOne=price;
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
    		busAchievementNew.setProportion(String.valueOf((int)(proCreate*100)));
    		pro=pro-proCreate;
    		double priceCreate=price*proCreate;
    		priceOne=priceOne-priceCreate;
    		//录入人业绩金额
    		busAchievementNew.setPrice(String.valueOf((int)priceCreate));
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
    		busAchievementNew.setProportion(String.valueOf((int)(proRecord*100)));
    		pro=pro-proRecord;
    		double priceRecord=price*proRecord;
    		priceOne=priceOne-priceRecord;
    		//维护人业绩金额
    		busAchievementNew.setPrice(String.valueOf((int)priceRecord));
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
    		busAchievementNew.setProportion(String.valueOf((int)(proKey*100)));
    		pro=pro-proKey;
    		double priceKey=price*proKey;
    		priceOne=priceOne-priceKey;
    		//钥匙人业绩金额
    		busAchievementNew.setPrice(String.valueOf((int)priceKey));
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
    		busAchievementNew.setProportion(String.valueOf((int)(proExploration*100)));
    		pro=pro-proExploration;
    		double priceExploration=price*proExploration;
    		priceOne=priceOne-priceExploration;
    		//实勘人业绩金额
    		busAchievementNew.setPrice(String.valueOf((int)priceExploration));
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
    		busAchievementNew.setProportion(String.valueOf((int)(pro*100)));
    		//促成人占剩下的全部
    		busAchievementNew.setPrice(String.valueOf((int)priceOne));
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
    	}
    	//查询
        List<BusAchievement> list = busAchievementService.listMyAchievement(map);
        
        for(BusAchievement busAchievement:list){
        	
        	//转换时间格式
         	SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         	Date date = fmt.parse(busAchievement.getCreateTime());
    		String  sre= fmt.format(date);
    		busAchievement.setCreateTime(sre);
    		//判断生成角色名称
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
	 * 查看审核成交业绩
	 * @param map
	 * @throws ParseException 
	 * */
    @PreAuthorize("hasAuthority('achievement:examineAchievement')")
    @PostMapping("/examineAchievement")
    public Result examineAchievement(Principal user,@RequestBody Map<String,Object> map){
    	PageHelper.startPage(Integer.valueOf( map.get("page").toString()), Integer.valueOf( map.get("size").toString()));
    	List<BusAchievement> list = busAchievementService.examineAchievement(map);
    	  for(BusAchievement busAchievement:list){
          	//判断角色名称
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
	 * 查看分店业绩
	 * @param map
	 * @throws ParseException 
	 * */
    @PreAuthorize("hasAuthority('achievement:listAchievementTow')")
    @PostMapping("/listAchievementTow")
    public Result listAchievementTow(Principal user,@RequestBody Map<String,Object> map) throws ParseException{
    	PageHelper.startPage(Integer.valueOf( map.get("page").toString()), Integer.valueOf( map.get("size").toString()));
    	List<BusAchievement> list = busAchievementService.listAchievementTow(map);
    	  for(BusAchievement busAchievement:list){
          	//转换时间格式
           	SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           	Date date = fmt.parse(busAchievement.getCreateTime());
      		String  sre= fmt.format(date);
      		busAchievement.setCreateTime(sre);
          	//判断角色名称
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
	 * 查询全部门店的业绩
	 * @param map
	 * @throws ParseException 
	 * */
    @PreAuthorize("hasAuthority('achievement:listStoreAllAchievement')")
    @PostMapping("/listStoreAllAchievement")
    public Result listStoreAllAchievement(Principal user,@RequestBody Map<String,Object> map){
    	PageHelper.startPage(Integer.valueOf( map.get("page").toString()), Integer.valueOf( map.get("size").toString()));
    	List<BusAchievement> list = busAchievementService.listStoreAllAchievement(map);
    	//全部门店总业绩
    	int priceSum=0;
    	//计算全部门店总业绩
    	for(BusAchievement busAchievement:list){   			
    		  	if(busAchievement.getPrice()!=null){    		  		
    		  		priceSum=priceSum+Integer.valueOf(busAchievement.getPrice());
    		  	}else{
    		  		
    		  		busAchievement.setPrice("0");
    		  	}
          }
    	//添加全部门店总业绩
       	for(BusAchievement busAchievement:list){
       		busAchievement.setSumprice(String.valueOf(priceSum));
       	}
    	PageInfo<BusAchievement> pageInfo = new PageInfo<BusAchievement>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
	/**
	 * 业绩2门店查询
	 * @param map
	 * @throws ParseException 
	 * */
    @PreAuthorize("hasAuthority('achievement:listStore')")
    @PostMapping("/listStore")
    public Result listStore(Principal user){
    	 Map<String,Object> map=new HashMap<String,Object>();
    	 List<Store> list=storeService.ListBaseStore(map);
    	 return ResultGenerator.genOkResult(list);
    }
    

}
