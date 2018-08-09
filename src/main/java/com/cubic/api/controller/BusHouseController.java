package com.cubic.api.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.cubic.api.model.BusExamine;
import com.cubic.api.model.BusHouse;
import com.cubic.api.model.BusHouseClicklog;
import com.cubic.api.model.User;
import com.cubic.api.service.BusExamineService;
import com.cubic.api.service.BusHouseClicklogService;
import com.cubic.api.service.BusHouseService;
import com.cubic.api.service.MessageService;
import com.cubic.api.service.UserService;
import com.cubic.api.util.MessageConstant;
import com.cubic.api.util.NumberUtil;
import com.cubic.api.util.OSSUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;



/**
 * 房源接口
 * @author cubic
 * @date 2018/07/12
 */
@RestController
@RequestMapping("/vanke/house")
public class BusHouseController {
    @Resource
    private BusHouseService busHouseService;
    @Resource
    private BusHouseClicklogService busHouseClicklogService;
    @Resource
    private UserService userService;
    @Resource
    private BusExamineService busExamineService;
    @Resource
    private MessageService messageService;

   /**
    * 创建房源信息
    * @param busHouse
    * 
    * */
    @PreAuthorize("hasAuthority('house:save')")
    @PostMapping("/save")
    public Result add(Principal user,@RequestBody BusHouse busHouse) {
    	
    	//创建人账号名
    	busHouse.setCreateUserName(user.getName());
    	//维护人账号名
    	busHouse.setRecordUserName(user.getName());

    	//验证是否为空
    	
    	List<BusHouse> findlist=busHouseService.findIsAddress(busHouse);   
    	if(findlist!=null && findlist.size()!=0){
    		return ResultGenerator.genOkResult("0");
    	}
    	if(null!=busHouse.getChaoxiang()){//朝向
			   if("1".equals(busHouse.getChaoxiang())){
				   busHouse.setChaoxiang("正南");
			   }else  if("2".equals(busHouse.getChaoxiang())){			
				   busHouse.setChaoxiang("正北");
			   }else  if("3".equals(busHouse.getChaoxiang())){
				   busHouse.setChaoxiang("正东");
			   }else  if("4".equals(busHouse.getChaoxiang())){
				   busHouse.setChaoxiang("正西");
			   }else  if("5".equals(busHouse.getChaoxiang())){
				   busHouse.setChaoxiang("东南");
			   }else  if("6".equals(busHouse.getChaoxiang())){
				   busHouse.setChaoxiang("西南");
			   }else  if("7".equals(busHouse.getChaoxiang())){
				   busHouse.setChaoxiang("东北");
			   }else  if("8".equals(busHouse.getChaoxiang())){
				   busHouse.setChaoxiang("西北");
			   }else  if("9".equals(busHouse.getChaoxiang())){
				   busHouse.setChaoxiang("东西");
			   }else  if("10".equals(busHouse.getChaoxiang())){
				   busHouse.setChaoxiang("南北");
			   }else  if("11".equals(busHouse.getChaoxiang())){
				   busHouse.setChaoxiang("东南北");
			   }else  if("12".equals(busHouse.getChaoxiang())){
				   busHouse.setChaoxiang("西南北");
			   }else  if("13".equals(busHouse.getChaoxiang())){
				   busHouse.setChaoxiang("东南西北");
			   }
		   }
    	   if(null!=busHouse.getGrade()){//房屋等级
    		   if("1".equals(busHouse.getGrade())){    			   
    			   busHouse.setGrade("A");
    		   }else if ("2".equals(busHouse.getGrade())){
    			   busHouse.setGrade("B");
    		   }else if ("3".equals(busHouse.getGrade())){
    			   busHouse.setGrade("C");
    		   }    		   
    	   }
    	   //如果是买卖计算为万
    	   if("1".equals(busHouse.getType())){
	    	 //转换成整数,例:(150.85万 -转换成-1508500)   		   
				double d = Double.parseDouble(busHouse.getPrice())*10000;
				BigDecimal bd = new BigDecimal(d);
				int d1 = bd.setScale(2, BigDecimal.ROUND_HALF_UP).intValue();
				busHouse.setPrice(String.valueOf(d1));
	    	   
    	   }
    	   busHouseService.insertBusHouse(busHouse);
    	if(busHouse.getId()!=null){
    		//根据id得到房源编号并更新
    		String num = NumberUtil.geoEquipmentNo("H",busHouse.getId());
        	BusHouse busHouseNew=new BusHouse();
        	busHouseNew.setId(busHouse.getId());
        	busHouseNew.setNumber(num);
        	busHouseService.update(busHouseNew);
    	}
    	
    	
        return ResultGenerator.genOkResult("添加成功");
    }

//    @DeleteMapping("/{id}")
//    public Result delete(@PathVariable Long id) {
//    	busHouseService.deleteById(id);
//        return ResultGenerator.genOkResult();
//    }
    
    /**
     * 提交房源状态更改申请
     * @param busHouse
     * 状态:(0:普通,1:无效,2:提交无效待审核,3:提交取消无效待审核)
     * */
    @PreAuthorize("hasAuthority('house:updateState')")
    @PostMapping("/updateState")
    public Result updateState(Principal user,@RequestBody BusHouse busHouse) {
    	BusExamine busExamine =new BusExamine(); 
    	String msgContent="";
    	String url=MessageConstant.MESSAGE_AUDIT_URL;
		if(busHouse.getState().equals("1")){//设置为无效房源
			busExamine.setType("3");   
			busHouse.setState("2");
			msgContent = MessageConstant.MESSAGE_HOUSE_INVALID;
		}else if(busHouse.getState().equals("0")){//取消无效房源
			busExamine.setType("8"); 
			busHouse.setState("3");
			msgContent = MessageConstant.MESSAGE_HOUSE_NOINVALID;
		}
		//提交审核消息
		messageService.sendMessage("1", msgContent, url, user.getName());
		//待审核状态
		busHouseService.update(busHouse);
    	busExamine.setHouseId(busHouse.getId());
    	busExamine.setUserName(user.getName());
    	busExamineService.insertBusExamine(busExamine);
    	//提交到审核
        return ResultGenerator.genOkResult(busHouse.getState());
    }
    
    /**
     * 提交特殊房源申请
     * @param busHouse
     * 特殊房源状态:(0:否,1:是,2:提交特殊房源待审核,3:提交取消特殊待审核)
     * */
    @PreAuthorize("hasAuthority('house:updateIsSpecial')")
    @PostMapping("/updateIsSpecial")
    public Result updateIsSpecial(Principal user,@RequestBody BusHouse busHouse) {
    	BusExamine busExamine =new BusExamine(); 
    	String msgContent="";
    	String url=MessageConstant.MESSAGE_AUDIT_URL;
    	if(busHouse.getIsspecial().equals("1")){//设置为特殊房源
    		busExamine.setType("1");
    		busHouse.setIsspecial("2");
    		msgContent = MessageConstant.MESSAGE_HOUSE_SPECIAL;
    	}else if(busHouse.getIsspecial().equals("0")){//取消特殊房源
    		busExamine.setType("6");
    		busHouse.setIsspecial("3");
    		msgContent = MessageConstant.MESSAGE_HOUSE_NOSPECIAL;
    	}
    	//提交审核消息
		messageService.sendMessage("1", msgContent, url, user.getName());
		//待审核状态
		busHouseService.update(busHouse);
    	busExamine.setHouseId(busHouse.getId());
    	busExamine.setUserName(user.getName());
    	busExamineService.insertBusExamine(busExamine);
    	//提交到审核
        return ResultGenerator.genOkResult(busHouse.getIsspecial());
    }
    
    /**
     *提交优质房源申请
     * @param busHouse
     * 状态:(0:否,1:是,2:提交优质待审核,3:提交取消优质待审核)
     * */
    @PreAuthorize("hasAuthority('house:updateIsFine')")
    @PostMapping("/updateIsFine")
    public Result updateIsFine(Principal user,@RequestBody BusHouse busHouse) {
    	BusExamine busExamine =new BusExamine(); 
    	String msgContent="";
    	String url=MessageConstant.MESSAGE_AUDIT_URL;
    	if(busHouse.getIsfine().equals("0")){//取消优质房源
    		busExamine.setType("7");
            busHouse.setIsfine("3");
            msgContent = MessageConstant.MESSAGE_HOUSE_NOGOOD;
    	}else if(busHouse.getIsfine().equals("1")){//设置为优质房源
    		busExamine.setType("2");
            busHouse.setIsfine("2");
            msgContent = MessageConstant.MESSAGE_HOUSE_GOOD;
    	}  
    	//提交审核消息
		messageService.sendMessage("1", msgContent, url, user.getName());
		//待审核状态
    	busHouseService.update(busHouse);
    	busExamine.setHouseId(busHouse.getId());
    	busExamine.setUserName(user.getName());
    	busExamineService.insertBusExamine(busExamine);
    	//提交到审核
        return ResultGenerator.genOkResult(busHouse.getIsfine());
    }
    
    /**
     * 转赠房源维护人
     * @param busHouse
     * 
     * */
    @PreAuthorize("hasAuthority('house:updateRecordUser')")
    @PostMapping("/updateRecordUser")
    public Result updateRecordUser(@RequestBody BusHouse busHouse) {
    	BusHouse busHouseNew=new BusHouse();
    	if(null != busHouse){
    		if(busHouse.getId() != null && busHouse.getRecordUserName() != null){
    			busHouseNew.setId(busHouse.getId());
    			busHouseNew.setRecordUserName(busHouse.getRecordUserName());
    			busHouseService.updateRecordUser(busHouseNew);
    		}
    	}
        return ResultGenerator.genOkResult("修改成功");
    }
    /**
     * 修改房源基本信息
     * @param busHouse
     * 
     * */
    @PreAuthorize("hasAuthority('house:update')")
    @PostMapping("/update")
    public Result update(@RequestBody BusHouse busHouse) {
    	if(null != busHouse){
    		BusHouse newBean=busHouseService.findById(busHouse.getId());
    		if("1".equals(newBean.getType())){
	    		 //转换成整数,例:(150.85万 -转换成-1508500)
				double d = Double.parseDouble(busHouse.getPrice())*10000;
				BigDecimal bd = new BigDecimal(d);
				int d1 = bd.setScale(2, BigDecimal.ROUND_HALF_UP).intValue();
				busHouse.setPrice(String.valueOf(d1));
    		}
    		//记录价格修改日志
//    		if(!newBean.getPrice().equals(busHouse.getPrice())){
//    			Map<String,Object> newMap=new HashMap<String,Object>();
//    			//原价格
//    			int priceold = Integer.valueOf(newBean.getPrice());
//    			//新价格
//    			int pricenew = Integer.valueOf(busHouse.getPrice());
//    			//计算差价
//    			int lockprice = pricenew-priceold;
//    			//判断是上涨了还是下降了(1:上涨,2:下降)
//    			int upordown=0;
//    			if(lockprice>=1){
//    				upordown=1;
//    			}else if(lockprice<=-1){
//    				upordown=2;
//    			}
//    			newMap.put("priceold", priceold);
//    			newMap.put("pricenew", pricenew);
//    			newMap.put("lockprice", lockprice);
//    			newMap.put("upordown", upordown);
//    			busHouseService.insertPriceLog(newMap);
//    		}
    		if(null!=busHouse.getChaoxiang()){//朝向
 			   if("1".equals(busHouse.getChaoxiang())){
				   busHouse.setChaoxiang("正南");
			   }else  if("2".equals(busHouse.getChaoxiang())){			
				   busHouse.setChaoxiang("正北");
			   }else  if("3".equals(busHouse.getChaoxiang())){
				   busHouse.setChaoxiang("正东");
			   }else  if("4".equals(busHouse.getChaoxiang())){
				   busHouse.setChaoxiang("正西");
			   }else  if("5".equals(busHouse.getChaoxiang())){
				   busHouse.setChaoxiang("东南");
			   }else  if("6".equals(busHouse.getChaoxiang())){
				   busHouse.setChaoxiang("西南");
			   }else  if("7".equals(busHouse.getChaoxiang())){
				   busHouse.setChaoxiang("东北");
			   }else  if("8".equals(busHouse.getChaoxiang())){
				   busHouse.setChaoxiang("西北");
			   }else  if("9".equals(busHouse.getChaoxiang())){
				   busHouse.setChaoxiang("东西");
			   }else  if("10".equals(busHouse.getChaoxiang())){
				   busHouse.setChaoxiang("南北");
			   }else  if("11".equals(busHouse.getChaoxiang())){
				   busHouse.setChaoxiang("东南北");
			   }else  if("12".equals(busHouse.getChaoxiang())){
				   busHouse.setChaoxiang("西南北");
			   }else  if("13".equals(busHouse.getChaoxiang())){
				   busHouse.setChaoxiang("东南西北");
			   }
 		   }
    		    //修改
    			busHouseService.update(busHouse);  		
    	}
        return ResultGenerator.genOkResult("1");
    }
    
    
    /**
     * 获取共享池里的房源
     * @param busHouse
     * 
     * */
    @PreAuthorize("hasAuthority('house:updateIsShare')")
    @PostMapping("/updateIsShare")
    public Result updateIsShare(Principal user,@RequestBody BusHouse busHouse) {
    	if(null != busHouse){
	    		busHouse.setRecordUserName(user.getName());
	    		busHouse.setIsshare("0");
    			busHouseService.updateShareState(busHouse);  		
    	}
        return ResultGenerator.genOkResult("修改成功");
    }
    /**
     * 录入实勘图片
     * @param busHouse
     * 
     * */
    @PreAuthorize("hasAuthority('house:updateImg')")
    @PostMapping("/updateImg")
    public Result updateImg(Principal user,@RequestBody BusHouse busHouse) {
    	StringBuffer url=new StringBuffer();
    	BusExamine busExamine =new BusExamine(); 
    	//循环读取室的实勘图片url
    	for(String urltext:busHouse.getShiImgList()){
    		url.append(OSSUtil.uploadOSSToInputStream(urltext,"house")+",");
    	}
    	busExamine.setShiimg(url.toString());
    	url.setLength(0);
    	//循环读取厅的实勘图片url
    	for(String urltext:busHouse.getTingImgList()){
    		url.append(OSSUtil.uploadOSSToInputStream(urltext,"house")+",");
    	}
    	busExamine.setTingimg(url.toString());
    	url.setLength(0);
    	//循环读取卫的实勘图片url
    	for(String urltext:busHouse.getWeiImgList()){
    		url.append(OSSUtil.uploadOSSToInputStream(urltext,"house")+",");
    	}
    	busExamine.setWeiimg(url.toString());
    	url.setLength(0);
    	//循环读取厨的实勘图片url
    	for(String urltext:busHouse.getChuImgList()){
    		url.append(OSSUtil.uploadOSSToInputStream(urltext,"house")+",");
    	}
    	busExamine.setChuimg(url.toString());
    	url.setLength(0);
    	//循环读取户型的实勘图片url
    	for(String urltext:busHouse.getHuxingImgList()){
    		url.append(OSSUtil.uploadOSSToInputStream(urltext,"house")+",");
    	}
    	busExamine.setHuxingimg(url.toString());
    	url.setLength(0);
    	//循环读取其他的实勘图片url
    	for(String urltext:busHouse.getOtherImgList()){
    		url.append(OSSUtil.uploadOSSToInputStream(urltext,"house")+",");
    	}
    	busExamine.setOtherimg(url.toString());
    	url.setLength(0);
    	//提交审核消息
    	String msgContent=MessageConstant.MESSAGE_HOUSE_REALIMG;
    	String urlin=MessageConstant.MESSAGE_HOUSE_URL+busHouse.getId();
		messageService.sendMessage("2", msgContent, urlin, user.getName());
    	busExamine.setHouseId(busHouse.getId());
    	busExamine.setType("4");
    	busExamine.setUserName(user.getName());
    	
    	busExamineService.insertBusExamine(busExamine);
        return ResultGenerator.genOkResult("提交待审核");
    }
  
    
    /**
     * 更新钥匙状态和拥有者
     * 
     * ,@RequestBody BusHouse busHouse
     * */
    @PreAuthorize("hasAuthority('house:updateKey')")
    @PostMapping("/updateKey")
    public Result updateKey(Principal user,@RequestBody BusHouse busHouse) {
    		busHouse.setIskey("1");
    		busHouse.setKeyUserName(user.getName());
  			busHouseService.updateKey(busHouse);
        return ResultGenerator.genOkResult("1");
    }
    /**
     * 更新钥匙状态和拥有者
     * 
     * ,@RequestBody BusHouse busHouse
     * */
    @PreAuthorize("hasAuthority('house:updateCancelKey')")
    @PostMapping("/updateCancelKey")
    public Result updateCancelKey(Principal user,@RequestBody BusHouse busHouse){
    	
			busHouseService.updateCancelKey(busHouse);

        return ResultGenerator.genOkResult("0");
    }
	 /**
     * 点击查看详细联系方式及房主姓名
     * @param map
     * 
     * */
    @PreAuthorize("hasAuthority('house:detailPhone')")
    @PostMapping("/detailPhone")
    public Result detailPhone(Principal user,@RequestBody Map<String,Object> map) {
    	map.put("clickusername", user.getName());
    	BusHouse busHouse = busHouseService.DetailContacts(map);
    	if(busHouse.getClickcount()==20){
    		return ResultGenerator.genOkResult("您今日查询联系信息的次数已用完");   		
    	}else if (busHouse.getClickcount()<20){
    		
    		//日志信息
    		BusHouseClicklog busHouseClicklog=new BusHouseClicklog();
    		busHouseClicklog.setClickUserName(user.getName());
    		busHouseClicklog.setHouseId(Long.parseLong(map.get("houseId").toString()));
    		busHouseClicklog.setRecordUserName(busHouse.getRecordUserName());
    		//日志类型(1:联系方式查看,2:地址查看)
    		busHouseClicklog.setType("1");
    		//记录查询日志
    		busHouseClicklogService.insertClickLog(busHouseClicklog);
    		busHouse.setRecordUserName(null);
    		BusHouse busHouseNew = busHouseService.DetailContacts(map);
    		busHouse.setClickcount(busHouseNew.getClickcount());
    	}
    	return ResultGenerator.genOkResult(busHouse);
    }
	
	 /**
    * 点击查看房屋地址
    * @param map
    * 
    * */
    @PreAuthorize("hasAuthority('house:detailAddress')")
    @PostMapping("/detailAddress")
    public Result detailAddress(Principal user,@RequestBody Map<String,Object> map) {
    	map.put("clickusername", user.getName());
    	BusHouse busHouse = busHouseService.DetailAddress(map);
    	StringBuffer stringtext=new StringBuffer();
    //拼接详细地址
    	if(null!=busHouse.getRegionName()){
    		//区域名
    		stringtext.append(busHouse.getRegionName()+" ");
    	}
    	if(null!=busHouse.getStreetName()){
    		//街道名
    		stringtext.append(busHouse.getStreetName()+" ");
    	}
    	if(null!=busHouse.getXiaoquName()){
    		//小区名
    		stringtext.append(busHouse.getXiaoquName()+" ");
    	}
    	if(null!=busHouse.getAddress()){
    		//详细地址
    		stringtext.append(busHouse.getAddress()+" ");
    	}
    	if(null!=busHouse.getNumfloor()){
    		//楼号
    		stringtext.append(busHouse.getNumfloor()+"号楼 ");
    	}
    	if(null!=busHouse.getNumunit()){
    		//单元号
    		stringtext.append(busHouse.getNumunit()+"单元 ");
    	}
    	if(null!=busHouse.getFloor()){
    		//门牌号
    		
    		stringtext.append(busHouse.getFloor()+"楼 ");
    	}
    	if(null!=busHouse.getNumhousehold()){
    		//门牌号
    		
    		stringtext.append(busHouse.getNumhousehold()+"号 ");
    	}
    
    	//拼接
    	busHouse.setAddressText(stringtext.toString());
    	
    	//判断次数
    	if(busHouse.getClickcount()==20){
    		return ResultGenerator.genOkResult("您今日查询房屋地址的次数已用完");
    	}else if (busHouse.getClickcount()<20){
    		//日志
    		BusHouseClicklog busHouseClicklog=new BusHouseClicklog();
    		busHouseClicklog.setClickUserName(user.getName());
    		busHouseClicklog.setHouseId(Long.parseLong(map.get("houseId").toString()));
    		busHouseClicklog.setRecordUserName(busHouse.getRecordUserName());
    		//日志类型(1:联系方式查看,2:地址查看)
    		busHouseClicklog.setType("2");
    		//记录查询日志
    		busHouseClicklogService.insertClickLog(busHouseClicklog);
    		busHouse.setRecordUserName(null);
    		BusHouse busHouseNew = busHouseService.DetailAddress(map);
    		busHouse.setClickcount(busHouseNew.getClickcount());
    	}
        return ResultGenerator.genOkResult(busHouse);
    }
    /**
     * 查询详细信息
     * @param  
     * @throws ParseException 
     * @RequestBody map
     * */
    @PreAuthorize("hasAuthority('house:detail')")
    @PostMapping("/detail")
    public Result detail(Principal user,@RequestBody Map<String,String> map) throws ParseException {
    	map.put("username", user.getName());
    	BusHouse busHouseNew=busHouseService.detailHouse(map);
    	
    	//关注状态(0:未关注,1:关注)
	    if(!"0".equals(busHouseNew.getLikeType())){
	    	busHouseNew.setLikeType("1");
	    }
	    //3:没有待审核的实勘图
	    if(busHouseNew.getExamineState()==null){
	    	busHouseNew.setExamineState("3");
	    }
	    //判断钥匙人是否存在
	    if(busHouseNew.getKeyUserName()!=null){
	    	//判断当前登陆人是否是钥匙人
	    	if(user.getName().equals(busHouseNew.getKeyUserName())){
	    		busHouseNew.setKeyUserType("1");
	    	}else{
	    		busHouseNew.setKeyUserType("0");
	    	}	    	
	    }
    	//转换时间格式
     	SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     	Date date = fmt.parse(busHouseNew.getCreateTime());
		String  sre= fmt.format(date);
		
		busHouseNew.setCreateTime(sre);
		
		//按照买卖和租赁类型返回价格单位
		if("1".equals(busHouseNew.getType())){
			if(null !=busHouseNew.getPrice()&& null !=busHouseNew.getAreas()){
				//转换成万为单位
				double d = Double.parseDouble(busHouseNew.getPrice())/10000;
				BigDecimal bd = new BigDecimal(d);
				double d1 = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				
				double d2=d1;
				int d3=(int)d1;
				if(d2==d3){
					busHouseNew.setPriceText(d3+"万");
				}else{
					busHouseNew.setPriceText(d1+"万");
				}
				//计算每平米多少钱
				double doione = Double.parseDouble(busHouseNew.getPrice())/Double.parseDouble(busHouseNew.getAreas());
				BigDecimal bdone = new BigDecimal(doione);
				int done = bdone.setScale(2, BigDecimal.ROUND_HALF_UP).intValue();
				busHouseNew.setPriceOneText(done+"元/平");
				//改变前台的显示形式
				if(d2==d3){
					busHouseNew.setPrice(String.valueOf(d3));
				}else{
					busHouseNew.setPrice(String.valueOf(d1));
				}
			}
		}else if("2".equals(busHouseNew.getType())){	
			//租赁价格单位为每月多少钱
			if(null !=busHouseNew.getPrice()){
				busHouseNew.setPriceText(busHouseNew.getPrice()+"元/月");
			}
		}
		//图片显示
		StringBuffer urltext=new StringBuffer();
	     if(null!=busHouseNew.getShiimg() && !"".equals(busHouseNew.getShiimg())){       	    	 
	    	 urltext.append(busHouseNew.getShiimg());
	     }
	     if(null!=busHouseNew.getTingimg() && !"".equals(busHouseNew.getTingimg())){		        	    	 
           urltext.append(busHouseNew.getTingimg());
       	     }
	     if(null!=busHouseNew.getWeiimg() && !"".equals(busHouseNew.getWeiimg())){	    	 
	    	 urltext.append(busHouseNew.getWeiimg());
	     }
	     if(null!=busHouseNew.getChuimg() && !"".equals(busHouseNew.getChuimg())){
	    	 
	    	 urltext.append(busHouseNew.getChuimg());
	     }
		 if(null!=busHouseNew.getHuxingimg() && !"".equals(busHouseNew.getHuxingimg())){
					    	 
			urltext.append(busHouseNew.getHuxingimg());
					     }
		 if(null!=busHouseNew.getOtherimg() && !"".equals(busHouseNew.getOtherimg())){
		  	 
		  	 urltext.append(busHouseNew.getOtherimg());
		   }				 
		 busHouseNew.setImgurl(Arrays.asList(urltext.toString().split(",")));
		 
		 if(urltext.toString()==null || "".equals(urltext.toString())){			 
			 busHouseNew.setImgurl(Arrays.asList(busHouseNew.getTitleimg()));
		 }
    	//是否是维护人(1:是,0:否)
    	if(user.getName().equals(busHouseNew.getRecordUserName())){
    		busHouseNew.setUser_type("1");
    		//查询可修改字段
    		BusHouse busHouseTow=busHouseService.detailUpdateInfo(busHouseNew);
    		//房主姓名1
    		busHouseNew.setOwner1(busHouseTow.getOwner1());
    		//房主联系方式
    		busHouseNew.setPhone(busHouseTow.getPhone());
    		//房主联系方式1
    		busHouseNew.setPhone1(busHouseTow.getPhone1());
    	}else{
    		busHouseNew.setUser_type("0");
    	}
    	return ResultGenerator.genOkResult(busHouseNew);
    }
    

    /**
     * 按条件查询列表 返回分页数据
     * @param  page  size map
     * @throws ParseException 
     * @RequestBody Map<String,Object> map
     * */
    @PreAuthorize("hasAuthority('house:list')")
    @PostMapping("/list")
    public Result list(Principal user,@RequestBody Map<String,Object> map) throws ParseException {
    	map.put("userName", user.getName());
        PageHelper.startPage(Integer.valueOf( map.get("page").toString()), Integer.valueOf( map.get("size").toString()));
        Map<String,Object> mapnew=resMap(map);
        List<BusHouse> list = busHouseService.ListBusHouse(mapnew);
        
         //显示图片
        for(BusHouse bushouses:list){  
    		if("1".equals(bushouses.getType())){
    			if(null !=bushouses.getPrice()&& null !=bushouses.getAreas()){
    				double d = Double.parseDouble(bushouses.getPrice())/10000;
    				BigDecimal bd = new BigDecimal(d);
    				double d1 = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

    				double d2=d1;
    				int d3=(int)d1;
    				if(d2==d3){
    					bushouses.setPriceText(d3+"万");
    				}else{
    					bushouses.setPriceText(d1+"万");
    				}
    				//计算每平米多少钱
    				double doione = Double.parseDouble(bushouses.getPrice())/Double.parseDouble(bushouses.getAreas());
    				BigDecimal bdone = new BigDecimal(doione);
    				int done = bdone.setScale(2, BigDecimal.ROUND_HALF_UP).intValue();
    				bushouses.setPriceOneText(done+"元/平");
    			}
    		}else if("2".equals(bushouses.getType())){			
    			if(null !=bushouses.getPrice()){
    				bushouses.setPriceText(bushouses.getPrice()+"元/月");
    			}
    		}
        	     StringBuffer urltext=new StringBuffer();
        	     if(null!=bushouses.getShiimg()){       	    	 
        	    	 urltext.append(bushouses.getShiimg());
        	     }
			     if(null!=bushouses.getTingimg()){		        	    	 
		            urltext.append(","+bushouses.getTingimg());
		        	     }
			     if(null!=bushouses.getWeiimg()){	    	 
			    	 urltext.append(","+bushouses.getWeiimg());
			     }
			     if(null!=bushouses.getChuimg()){
			    	 
			    	 urltext.append(","+bushouses.getChuimg());
			     }
				 if(null!=bushouses.getHuxingimg()){
							    	 
					urltext.append(","+bushouses.getHuxingimg());
							     }
				 if(null!=bushouses.getOtherimg()){
				  	 
				  	 urltext.append(","+bushouses.getOtherimg());
				   }				 
				 bushouses.setImgurl(Arrays.asList(urltext.toString().split(",")));
        }
        PageInfo<BusHouse> pageInfo = new PageInfo<BusHouse>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
    
    //房源条件筛选转换
   public Map<String,Object> resMap(Map<String,Object> map){
	   if(null != map){
		   if(null!=map.get("positionType")){//位置条件
			   map.put("regionCode", map.get("positionType"));
			   
		   }
		   //如果是买卖房源乘以10000,变成以万为单位
		   if(map.get("type") != null && "1".equals(map.get("type").toString())){
			   if(map.get("priceUp")!=null && !"".equals(map.get("priceUp"))){
				   map.put("priceUp", (Integer.parseInt(map.get("priceUp").toString())*10000));			
			   }
			   if(map.get("priceDown")!=null && !"".equals(map.get("priceDown"))){
				   map.put("priceDown", (Integer.parseInt(map.get("priceDown").toString())*10000));			
			   }
			   
		   }
//		   if(null !=map.get("priceType")){
//			   if(map.get("type") != null && "1".equals(map.get("type").toString())){//买卖房源
//				   if("1".equals(map.get("priceType").toString())){//30万以下
//					   map.put("priceDown",300000);
//				   }else if("2".equals(map.get("priceType").toString())){//30万-40万
//					   map.put("priceUp",300000);
//					   map.put("priceDown",400000);
//				   }else if("3".equals(map.get("priceType").toString())){//40万-50万
//					   map.put("priceUp",400000);
//					   map.put("priceDown",500000);
//				   }else if("4".equals(map.get("priceType").toString())){//50万-60万
//					   map.put("priceUp",500000);
//					   map.put("priceDown",600000);
//				   }else if("5".equals(map.get("priceType").toString())){//60万-80万
//					   map.put("priceUp",600000);
//					   map.put("priceDown",800000);
//				   }else if("6".equals(map.get("priceType").toString())){//80万-100万
//					   map.put("priceUp",800000);
//					   map.put("priceDown",1000000);
//				   }else if("7".equals(map.get("priceType").toString())){//100万-150万
//					   map.put("priceUp",1000000);
//					   map.put("priceDown",1500000);
//				   }else if("8".equals(map.get("priceType").toString())){//150万-200万
//					   map.put("priceUp",1500000);
//					   map.put("priceDown",2000000);
//				   }else if("9".equals(map.get("priceType").toString())){//200万以上
//					   map.put("priceUp",2000000);
//				   }
//				   
//			   }else if(map.get("type") != null && "2".equals(map.get("type").toString())){
//				   if("1".equals(map.get("priceType").toString())){//500以下
//					   map.put("priceDown",500);
//				   }else if("2".equals(map.get("priceType").toString())){//500元-800元
//					   map.put("priceUp",500);
//					   map.put("priceDown",800);
//				   }else if("3".equals(map.get("priceType").toString())){//800元-1500元
//					   map.put("priceUp",800);
//					   map.put("priceDown",1500);
//				   }else if("4".equals(map.get("priceType").toString())){//1500元-2000元
//					   map.put("priceUp",1500);
//					   map.put("priceDown",2000);
//				   }else if("5".equals(map.get("priceType").toString())){//2000元-3000元
//					   map.put("priceUp",2000);
//					   map.put("priceDown",3000);
//				   }else if("6".equals(map.get("priceType").toString())){//3000元-5000元
//					   map.put("priceUp",3000);
//					   map.put("priceDown",5000);
//				   }else if("7".equals(map.get("priceType").toString())){//5000元以上
//					   map.put("priceUp",5000);
//				   }
//			   }
//		   }
		   
		   if(null !=map.get("areaType")){
			   if("1".equals(map.get("areaType").toString())){//50平以下
				   map.put("areasDown",50);
			   }else  if("2".equals(map.get("areaType").toString())){//50平-70平
				   map.put("areasUp",50);
				   map.put("areasDown",70);
			   }else  if("3".equals(map.get("areaType").toString())){//70平-90平
				   map.put("areasUp",70);
				   map.put("areasDown",90);
			   }else  if("4".equals(map.get("areaType").toString())){//90平-110平
				   map.put("areasUp",90);
				   map.put("areasDown",110);
			   }else  if("5".equals(map.get("areaType").toString())){//110平-130平
				   map.put("areasUp",110);
				   map.put("areasDown",130);
			   }else  if("6".equals(map.get("areaType").toString())){//130平-150平
				   map.put("areasUp",130);
				   map.put("areasDown",150);
			   }else  if("7".equals(map.get("areaType").toString())){//150平-200平
				   map.put("areasUp",150);
				   map.put("areasDown",200);
			   }else  if("8".equals(map.get("areaType").toString())){//200平以上
				   map.put("areasUp",200);
				
			   }
		   }
		   
		   if(null!=map.get("chaoxiangType")){//朝向条件
			   if("1".equals(map.get("chaoxiangType").toString())){
				   map.put("chaoxiang", "正南");
			   }else  if("2".equals(map.get("chaoxiangType").toString())){
				   map.put("chaoxiang", "正北");
			   }else  if("3".equals(map.get("chaoxiangType").toString())){
				   map.put("chaoxiang", "正东");
			   }else  if("4".equals(map.get("chaoxiangType").toString())){
				   map.put("chaoxiang", "正西");
			   }else  if("5".equals(map.get("chaoxiangType").toString())){
				   map.put("chaoxiang", "东南");
			   }else  if("6".equals(map.get("chaoxiangType").toString())){
				   map.put("chaoxiang", "西南");
			   }else  if("7".equals(map.get("chaoxiangType").toString())){
				   map.put("chaoxiang", "东北");
			   }else  if("8".equals(map.get("chaoxiangType").toString())){
				   map.put("chaoxiang", "西北");
			   }else  if("9".equals(map.get("chaoxiangType").toString())){
				   map.put("chaoxiang", "东西");
			   }else  if("10".equals(map.get("chaoxiangType").toString())){
				   map.put("chaoxiang", "南北");
			   }else  if("11".equals(map.get("chaoxiangType").toString())){
				   map.put("chaoxiang", "东南北");
			   }else  if("12".equals(map.get("chaoxiangType").toString())){
				   map.put("chaoxiang", "西南北");
			   }else  if("13".equals(map.get("chaoxiangType").toString())){
				   map.put("chaoxiang", "东南西北");
		      }
		   }
		   
	   }
	   
	   
	   return map;
   }
    
    /**
     * 查询我的维护房源
     * @param  page  size map
     * @RequestBody Map<String,Object> map
     * */
    @PreAuthorize("hasAuthority('house:myRecordHouseList')")
    @PostMapping("/myRecordHouseList")
    public Result myRecordHouselist(Principal user,@RequestBody Map<String,Object> map) {
	      
	      PageHelper.startPage(Integer.valueOf( map.get("page").toString()), Integer.valueOf( map.get("size").toString()));
	      

	      
	        List<BusHouse> list =new ArrayList<BusHouse>();
//	        //如果是店长的话查询本店所有维护的房源
//	        if(user.toString().indexOf("ROLE_MANAGER")!=-1){
//	        	  map.put("username", user.getName());
//	        	  list = busHouseService.listBusHouseRecord(map);
//	        	
//	        }else{//经济人查询自己维护的房源
	        	map.put("recordUserName", user.getName());
	        	list = busHouseService.ListBusHouse(map);
//	        }
    	  for(BusHouse busHouse:list){
    		  
      		if("1".equals(busHouse.getType())){
    			if(null !=busHouse.getPrice()&& null !=busHouse.getAreas()){
    				double d = Double.parseDouble(busHouse.getPrice())/10000;
    				BigDecimal bd = new BigDecimal(d);
    				double d1 = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    				
    				
    				double d2=d1;
    				int d3=(int)d1;
    				if(d2==d3){
    					busHouse.setPriceText(d3+"万");
    				}else{
    					busHouse.setPriceText(d1+"万");
    				}
    				//计算每平米多少钱
    				double doione = Double.parseDouble(busHouse.getPrice())/Double.parseDouble(busHouse.getAreas());
    				BigDecimal bdone = new BigDecimal(doione);
    				int done = bdone.setScale(2, BigDecimal.ROUND_HALF_UP).intValue();
    				busHouse.setPriceOneText(done+"元/平");
    				
    			}
    		}else if("2".equals(busHouse.getType())){			
    			if(null !=busHouse.getPrice()){
    				busHouse.setPriceText(busHouse.getPrice()+"元/月");
    			}
    		}
    	  }
	      PageInfo<BusHouse> pageInfo = new PageInfo<BusHouse>(list);
          return ResultGenerator.genOkResult(pageInfo);
    }
    
    /**
     * 查询我关注的房源
     * @param  page  size map
     * @RequestBody Map<String,Object> map
     * */
    @PreAuthorize("hasAuthority('house:myLikeHouseList')")
    @PostMapping("/myLikeHouseList")
    public Result myLikeHouselist(Principal user,@RequestBody Map<String,Object> map) {
	      map.put("userName", user.getName());
	      PageHelper.startPage(Integer.valueOf( map.get("page").toString()), Integer.valueOf( map.get("size").toString()));
	      List<BusHouse> list = busHouseService.listMyLikeHouse(map);
	      for(BusHouse busHouse:list){  		  
	     		if("1".equals(busHouse.getType())){
	    			if(null !=busHouse.getPrice()&& null !=busHouse.getAreas()){
	    				double d = Double.parseDouble(busHouse.getPrice())/10000;
	    				BigDecimal bd = new BigDecimal(d);
	    				double d1 = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	    				
	    			
	    				double d2=d1;
	    				int d3=(int)d1;
	    				if(d2==d3){
	    					busHouse.setPriceText(d3+"万");
	    				}else{
	    					busHouse.setPriceText(d1+"万");
	    				}
	    				//计算每平米多少钱
	    				double doione = Double.parseDouble(busHouse.getPrice())/Double.parseDouble(busHouse.getAreas());
	    				BigDecimal bdone = new BigDecimal(doione);
	    				int done = bdone.setScale(2, BigDecimal.ROUND_HALF_UP).intValue();
	    				busHouse.setPriceOneText(done+"元/平");
	    			}
	    		}else if("2".equals(busHouse.getType())){			
	    			if(null !=busHouse.getPrice()){
	    				busHouse.setPriceText(busHouse.getPrice()+"元/月");
	    			}
	    		}
	    	  }
    	  PageInfo<BusHouse> pageInfo = new PageInfo<BusHouse>(list);
          return ResultGenerator.genOkResult(pageInfo);
    }
    
    
    /**
     * 查询优质房源
     * @param  page  size map
     * @RequestBody Map<String,Object> map
     * */
    @PreAuthorize("hasAuthority('house:listIsfine')")
    @PostMapping("/listIsfine")
    public Result listIsfine(Principal user) {

    		Map<String,String> map = new HashMap<String,String>();
	      map.put("userName", user.getName());
	      List<BusHouse> list = busHouseService.listIsfine(map);
          return ResultGenerator.genOkResult(list);
    }
    /**
     * 查询要转让人列表
     * @param  page  size map
     * @RequestBody Map<String,Object> map
     * */
    @PreAuthorize("hasAuthority('house:listUser')")
    @PostMapping("/listUser")
    public Result listUser(@RequestBody Map<String,Object> map){  
    	List<User> list= new ArrayList<User>();
    	if(map.get("usertext")!=null && !"".equals(map.get("usertext"))){
	      list = userService.listUserInfo(map);
    	}
    	 return ResultGenerator.genOkResult(list);
    }

}
