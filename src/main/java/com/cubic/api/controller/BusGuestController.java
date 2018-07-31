package com.cubic.api.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cubic.api.core.response.Result;
import com.cubic.api.core.response.ResultGenerator;
import com.cubic.api.model.BusExamine;
import com.cubic.api.model.BusGuest;
import com.cubic.api.model.User;
import com.cubic.api.service.BusExamineService;
import com.cubic.api.service.BusGuestService;
import com.cubic.api.service.MessageService;
import com.cubic.api.service.UserService;
import com.cubic.api.util.MessageConstant;
import com.cubic.api.util.NumberUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 客源接口
 * @author cubic
 * @date 2018/07/13
 */
@RestController
@RequestMapping("/vanke/guest")
public class BusGuestController {
    @Resource
    private BusGuestService busGuestService;
    @Resource
    private BusExamineService busExamineService;
    @Resource
    private UserService userService;
    @Resource
    private MessageService messageService;
    /**
     * 创建客源
     * @param busGuest
     * 
     * */
    @PreAuthorize("hasAuthority('guest:insert')")
    @PostMapping("/insert")
    public Result add(Principal user,@RequestBody BusGuest busGuest) {
       	//创建人账号名
    	busGuest.setCreateUserName(user.getName());
    	//维护人账号名
    	busGuest.setRecordUserName(user.getName());
    	busGuestService.insertBusGuest(busGuest);
    	
    	//根据id得到客源编号并更新
    	String num = NumberUtil.geoEquipmentNo("G",busGuest.getId());
    	BusGuest busGuestNew=new BusGuest();
    	busGuestNew.setId(busGuest.getId());
    	busGuestNew.setNumber(num);
    	busGuestService.update(busGuestNew);
        return ResultGenerator.genOkResult("添加成功");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
    	busGuestService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PutMapping
    public Result update(@RequestBody BusGuest busGuest) {
    	busGuestService.update(busGuest);
        return ResultGenerator.genOkResult();
    }
    /**
     * 设置为无效房客源
     * @param busGuest
     * (0:普通,1:无效,2:提交待审核)
     * */
    @PreAuthorize("hasAuthority('guest:updateState')")
    @PostMapping("/updateState")
    public Result updateState(Principal user,@RequestBody BusGuest busGuest) {
    	//审核信息
    	BusExamine busExamine=new BusExamine();
    	busExamine.setGuestId(busGuest.getId());
    	String msgContent="";
    	String url=MessageConstant.MESSAGE_GUEST_URL+busGuest.getId();
    	if("1".equals(busGuest.getIskey())){
    		//客源无效状态2:无效房源
        	busGuest.setIskey("2");
    		busExamine.setType("5");
    		msgContent = MessageConstant.MESSAGE_GUEST_INVALID;
    	}else if("0".equals(busGuest.getIskey())){
    		busExamine.setType("9");
    		//客源无效状态3:取消无效房源
        	busGuest.setIskey("3");
    		msgContent = MessageConstant.MESSAGE_GUEST_NOINVALID;
    	}
    	//提交审核消息
    	messageService.sendMessage("1", msgContent, url, user.getName());
    
    	busGuestService.update(busGuest);
    	busExamine.setUserName(user.getName());
    	busExamineService.insertBusExamine(busExamine);
        return ResultGenerator.genOkResult(busGuest.getIskey());
    }
    
    /**
     * 获取共享池客源
     * @param busGuest
     * 
     * */
    @PreAuthorize("hasAuthority('guest:updateIsshareUser')")
    @PostMapping("/updateIsshareUser")
    public Result updateUser(Principal user,@RequestBody BusGuest busGuest) {
    	BusGuest busGuestNew=new BusGuest();
    	if(null != busGuest){
    		
    			busGuestNew.setId(busGuest.getId());
    			busGuestNew.setRecordUserName(user.getName());
    			busGuestService.updateRecordUser(busGuestNew);
    		
    	}
        return ResultGenerator.genOkResult("修改成功");
    }
    
    
    /**
     * 转让客源
     * @param busGuest
     * 
     * */
    @PreAuthorize("hasAuthority('guest:updateRecordUser')")
    @PostMapping("/updateRecordUser")
    public Result updateRecordUser(Principal user,@RequestBody BusGuest busGuest) {
    	BusGuest busGuestNew=new BusGuest();
    	if(null != busGuest){
    		if(busGuest.getId() != null && busGuest.getRecordUserName() != null){
    			busGuestNew.setId(busGuest.getId());
    			busGuestNew.setRecordUserName(busGuest.getRecordUserName());
    			busGuestService.updateRecordUser(busGuestNew);
    		}
    	}
        return ResultGenerator.genOkResult("修改成功");
    }
    /**
     * 详情查询
     * @param map
     * @throws ParseException 
     * 
     * */
    @PreAuthorize("hasAuthority('guest:detail')")
    @PostMapping("/detail")
    public Result detail(Principal user,@RequestBody Map<String,Object> map) throws ParseException {
    	BusGuest busGuest = busGuestService.findById(Long.valueOf(map.get("id").toString()));
    	//不为维护人不能看
    	if(!user.getName().equals(busGuest.getRecordUserName()) && !"1".equals(busGuest.getIsshare())){  		
    		return ResultGenerator.genOkResult(0);
    	}
	    	//转换委托时间格式
	     	SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	     	Date date = fmt.parse(busGuest.getCreateTime());
			String  sre= fmt.format(date);
			busGuest.setCreateTime(sre);
			//转换上次维护时间格式
	     	date = fmt.parse(busGuest.getRecordTime());
			sre= fmt.format(date);
			busGuest.setRecordTime(sre);
			
        return ResultGenerator.genOkResult(busGuest);
    }
    /**
     * 客源条件查询
     * @param map
     * @throws ParseException 
     * 
     * */
    @PreAuthorize("hasAuthority('guest:list')")
    @PostMapping("/list")
    public Result list(Principal user,@RequestBody Map<String,Object> map) throws ParseException {
    	PageHelper.startPage(Integer.valueOf( map.get("page").toString()), Integer.valueOf( map.get("size").toString()));
    	
    	map.put("recordUserName", user.getName());
   	    List<BusGuest> list = busGuestService.listBusGuest(map);

 		for(BusGuest busGuest:list){
 			
 	    	//转换委托时间格式
 	     	SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 	     	Date date = fmt.parse(busGuest.getCreateTime());
 			String  sre= fmt.format(date);
 			busGuest.setCreateTime(sre);
 			//转换上次维护时间格式
 	     	date = fmt.parse(busGuest.getRecordTime());
 			sre= fmt.format(date);
 			busGuest.setRecordTime(sre);
 			
//    			//如果当前登录人不为维护人就是合作人
//    			if(!busGuest.getRecordUserName().equals(user.getName())){	
//    				busGuest.setCollaboratorType("1");
//    			}
   		}
        PageInfo<BusGuest> pageInfo = new PageInfo<BusGuest>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
    
    /**
     * 查询要转让人列表
     * @param  page  size map
     * @RequestBody Map<String,Object> map
     * */
    @PreAuthorize("hasAuthority('guest:listUser')")
    @PostMapping("/listUser")
    public Result listUser(@RequestBody Map<String,Object> map){
    	PageHelper.startPage(Integer.valueOf( map.get("page").toString()), Integer.valueOf( map.get("size").toString()));
	      List<User> list = userService.listUserInfo(map);
	      PageInfo<User> pageInfo = new PageInfo<User>(list);
    	 return ResultGenerator.genOkResult(pageInfo);
    }
}
