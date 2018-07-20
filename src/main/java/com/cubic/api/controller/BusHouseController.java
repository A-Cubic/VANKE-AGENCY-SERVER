package com.cubic.api.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cubic.api.core.response.Result;
import com.cubic.api.core.response.ResultGenerator;
import com.cubic.api.model.BusExamine;
import com.cubic.api.model.BusHouse;
import com.cubic.api.model.BusHouseClicklog;
import com.cubic.api.service.BusExamineService;
import com.cubic.api.service.BusHouseClicklogService;
import com.cubic.api.service.BusHouseService;
import com.cubic.api.service.UserService;
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
    	//搜索文本条件
    	busHouse.setSearchtext("大连市"+busHouse.getRegionName()+busHouse.getStreetName()+busHouse.getXiaoquName()+busHouse.getAddress());
    	busHouseService.insertBusHouse(busHouse);

    	//根据id得到房源编号并更新
    	String num = NumberUtil.geoEquipmentNo("H",busHouse.getId());
    	BusHouse busHouseNew=new BusHouse();
    	busHouseNew.setId(busHouse.getId());
    	busHouseNew.setNumber(num);
    	busHouseService.update(busHouseNew);
    	
        return ResultGenerator.genOkResult("添加成功");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
    	busHouseService.deleteById(id);
        return ResultGenerator.genOkResult();
    }
    
    /**
     * 提交房源状态更改申请
     * @param busHouse
     * 状态:(0:普通,1:无效)
     * */
    @PreAuthorize("hasAuthority('house:updateState')")
    @PostMapping("/updateState")
    public Result updateState(Principal user,@RequestBody BusHouse busHouse) {
    	BusExamine busExamine =new BusExamine(); 
		if(busHouse.getState().equals("1")){//设置为无效房源
			busExamine.setType("3");    		
		}else if(busHouse.getState().equals("0")){//取消无效房源
			busExamine.setType("8"); 		
		}
    	
    	busExamine.setHouseId(busHouse.getId());
    	busExamine.setUserName(user.getName());
    	busExamineService.insertBusExamine(busExamine);
    	//提交到审核
        return ResultGenerator.genOkResult("提交审核成功");
    }
    
    /**
     * 提交特殊房源申请
     * @param busHouse
     * 特殊房源状态:(0:否,1:是)
     * */
    @PreAuthorize("hasAuthority('house:updateIsSpecial')")
    @PostMapping("/updateIsSpecial")
    public Result updateIsSpecial(Principal user,@RequestBody BusHouse busHouse) {
    	BusExamine busExamine =new BusExamine(); 
    	if(busHouse.getIsspecial().equals("1")){//设置为特殊房源
    		busExamine.setType("1");
    	}else if(busHouse.getIsspecial().equals("0")){//取消特殊房源
    		
    		busExamine.setType("6");
    	}
    	    		
    	busExamine.setHouseId(busHouse.getId());
    	busExamine.setUserName(user.getName());
    	busExamineService.insertBusExamine(busExamine);
    	//提交到审核
        return ResultGenerator.genOkResult("提交审核成功");
    }
    
    /**
     *提交优质房源申请
     * @param busHouse
     * 状态:(0:否,1:是)
     * */
    @PreAuthorize("hasAuthority('house:updateIsFine')")
    @PostMapping("/updateIsFine")
    public Result updateIsFine(Principal user,@RequestBody BusHouse busHouse) {
    	BusExamine busExamine =new BusExamine(); 
    	if(busHouse.getIsfine().equals("0")){//取消优质房源
    		busExamine.setType("7");
    		
    	}else if(busHouse.getIsfine().equals("1")){//设置为优质房源
    		busExamine.setType("2");
    	}  
    	busExamine.setHouseId(busHouse.getId());
    	busExamine.setUserName(user.getName());
    	busExamineService.insertBusExamine(busExamine);
    	//提交到审核
        return ResultGenerator.genOkResult("提交审核成功");
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
    			busHouseService.update(busHouse);  		
    	}
        return ResultGenerator.genOkResult("修改成功");
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
	    		busHouseService.updateRecordTime(busHouse);
    			busHouseService.update(busHouse);  		
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

        return ResultGenerator.genOkResult("更改成功");
    }
	 /**
     * 点击查看详细联系方式及房主姓名
     * @param map
     * 
     * */
    @PreAuthorize("hasAuthority('house:detailPhone')")
    @PostMapping("/detailPhone")
    public Result detailPhone(Principal user,@RequestBody Map<String,Object> map) {
    	BusHouse busHouse = busHouseService.DetailContacts(map);
    	if(busHouse.getClickcount()==20){
    		return ResultGenerator.genOkResult("您今日查询联系信息的次数已用完");   		
    	}else if (busHouse.getClickcount()<20){
    		
    		BusHouseClicklog busHouseClicklog=new BusHouseClicklog();
    		busHouseClicklog.setClickUserName(user.getName());
    		busHouseClicklog.setHouseId(Long.parseLong(map.get("houseId").toString()));
    		busHouseClicklog.setRecordUserName(busHouse.getRecordUserName());
    		busHouseClicklog.setType("1");
    		//记录查询日志
    		busHouseClicklogService.insertClickLog(busHouseClicklog);
    		busHouse.setRecordUserName(null);
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
    	BusHouse busHouse = busHouseService.DetailAddress(map);
    	if(busHouse.getClickcount()==20){
    		return ResultGenerator.genOkResult("您今日查询房屋地址的次数已用完");
    	}else if (busHouse.getClickcount()<20){
    		BusHouseClicklog busHouseClicklog=new BusHouseClicklog();
    		busHouseClicklog.setClickUserName(user.getName());
    		busHouseClicklog.setHouseId(Long.parseLong(map.get("houseId").toString()));
    		busHouseClicklog.setRecordUserName(busHouse.getRecordUserName());
    		busHouseClicklog.setType("2");
    		//记录查询日志
    		busHouseClicklogService.insertClickLog(busHouseClicklog);
    		busHouse.setRecordUserName(null);
    		
    	}
        return ResultGenerator.genOkResult(busHouse);
    }
    /**
     * 查询详细信息
     * @param  
     * @RequestBody busHouse
     * */
    @PreAuthorize("hasAuthority('house:detail')")
    @PostMapping("/detail")
    public Result detail(Principal user,@RequestBody BusHouse busHouse) {
    	BusHouse busHouseNew=busHouseService.findById(busHouse.getId());
    	busHouseNew.setAddress(null);
    	busHouseNew.setRegionId(null);
    	busHouseNew.setRegionName(null);
    	busHouseNew.setStreetId(null);
    	busHouseNew.setStreetName(null);
    	busHouseNew.setXiaoquName(null);
    	busHouseNew.setOwner(null);
    	busHouseNew.setPhone(null);
    	busHouseNew.setNumfloor(null);
    	busHouseNew.setNumhousehold(null);
    	busHouseNew.setNumunit(null);
    	//是否是维护人
    	if(user.getName().equals(busHouseNew.getRecordUserName())){
    		busHouseNew.setUser_ype("1");
    	}else{
    		busHouseNew.setUser_ype("0");
    	}
    	return ResultGenerator.genOkResult(busHouseNew);
    }
    

    /**
     * 按条件查询列表 返回分页数据
     * @param  page  size map
     * @RequestBody Map<String,Object> map
     * */
    @PreAuthorize("hasAuthority('house:list')")
    @PostMapping("/list")
    public Result list(Principal user,@RequestBody Map<String,Object> map) {
    	map.put("userName", user.getName());
        PageHelper.startPage(Integer.valueOf( map.get("page").toString()), Integer.valueOf( map.get("size").toString()));
        List<BusHouse> list = busHouseService.ListBusHouse(map);
         //显示图片
        for(BusHouse bushouses:list){        	
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
    
    /**
     * 测试接口
     * @param map
     * 
     * */
    @PostMapping("/test")
    public Result test(@RequestBody Map<String,Object> map) {
        return ResultGenerator.genOkResult(map);
    }
}
