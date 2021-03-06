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
import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cubic.api.core.jwt.JwtUtil;
import com.cubic.api.core.response.Result;
import com.cubic.api.core.response.ResultGenerator;
import com.cubic.api.model.BaseRegion;
import com.cubic.api.model.BaseStreet;
import com.cubic.api.model.LoginResponse;
import com.cubic.api.model.Store;
import com.cubic.api.model.User;
import com.cubic.api.model.home.CurrentUser;
import com.cubic.api.service.StoreService;
import com.cubic.api.service.UserRoleService;
import com.cubic.api.service.UserService;
import com.cubic.api.service.impl.UserDetailsServiceImpl;
import com.cubic.api.util.NumberUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author fei.yu
 * @date 2018/06/09
 */
@RestController
@RequestMapping("/vanke/user")
@Validated
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private UserDetailsServiceImpl userDetailsService;
    @Resource
    private UserRoleService userRoleService;
	@Resource
	private StoreService storeService;
    @Resource
    private JwtUtil jwtUtil;
    @PreAuthorize("hasAuthority('user:register')")
    @PostMapping("/register")
    public Result register(final Principal userPrincipal,@RequestBody @Valid final User user,
                           final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //noinspection ConstantConditions
            final String msg = bindingResult.getFieldError().getDefaultMessage();
            return ResultGenerator.genFailedResult(msg);
        } else {
        	//判断权限
  	  	  if(userPrincipal.toString().indexOf("ROLE_LEADER")!=-1
  	  			  ||userPrincipal.toString().indexOf("ROLE_SEC")!=-1
  	  			  ||userPrincipal.toString().indexOf("ROLE_ADMIN")!=-1){
  	  		  
  	  		 
  	  	  if(user.getPassword() == null || "".equals(user.getPassword())){
  	  		  user.setPassword("123456");
  	  	  }
  	  		  //注册
          String returnText=this.userService.registerUser(user);
          if(!"0".equals(returnText)){      	         
          	String num = NumberUtil.geoEquipmentNo("VK",user.getId());
        	User userNew=new User();
        	userNew.setId(user.getId());
        	userNew.setUser_no(num);
        	userService.update(userNew);
          }
          	  return ResultGenerator.genOkResult(returnText);
  		   	}else {
  		   	 return ResultGenerator.genOkResult("没有权限");
  		   	}
        	
//            return this.getToken(user);
        }
    }

    @PreAuthorize("hasAuthority('user:delete')")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable final Long id) {
        this.userService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PostMapping("/password")
    public Result validatePassword(@RequestBody final User user) {
        final User oldUser = this.userService.findById(user.getId());
        final boolean isValidate = this.userService.verifyPassword(user.getPassword(), oldUser.getPassword());
        return ResultGenerator.genOkResult(isValidate);
    }

    @PutMapping
    public Result update(@RequestBody final User user) {
        this.userService.update(user);
        return this.getToken(this.userService.findById(user.getId()));
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable final Long id) {
        final User user = this.userService.findById(id);
        return ResultGenerator.genOkResult(user);
    }

    @GetMapping("/info")
    public Result info(final Principal user) {
        final User userDB = this.userService.findDetailByUsername(user.getName());
        return ResultGenerator.genOkResult(userDB);
    }

   
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") final Integer page,
                       @RequestParam(defaultValue = "0") final Integer size) {
        PageHelper.startPage(page, size);
        final List<User> list = this.userService.findAllUserWithRole();
        //noinspection unchecked
        final PageInfo<User> pageInfo = new PageInfo<User>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    @PostMapping("/login")
    public Result login(@RequestBody final User user) {
        // {"username":"admin", "password":"admin123"}
        // {"email":"admin@qq.com", "password":"admin123"}
        if (user.getUsername() == null && user.getEmail() == null) {
            return ResultGenerator.genFailedResult("空账号");
        }
        if (user.getPassword() == null) {
            return ResultGenerator.genFailedResult("空密码");
        }
        User signUser=this.userService.findBy("username", user.getUsername());
        if ("0".equals(signUser.getSign())) {
            return ResultGenerator.genFailedResult("账号已冻结");
        }
        // 用户名登录
        User dbUser = null;
        if (user.getUsername() != null) {
            dbUser = this.userService.findBy("username", user.getUsername());
            if (dbUser == null) {
                return ResultGenerator.genFailedResult("账号不正确");
            }
        }
        // 邮箱登录
//        if (user.getEmail() != null) {
//            dbUser = this.userService.findBy("email", user.getEmail());
//            if (dbUser == null) {
//                return ResultGenerator.genFailedResult("错误的邮箱地址");
//            }
//            user.setUsername(dbUser.getUsername());
//        }
        // 验证密码
        //noinspection ConstantConditions
        if (!this.userService.verifyPassword(user.getPassword(), dbUser.getPassword())) {
            return ResultGenerator.genFailedResult("密码错误");
        }
        // 更新登录时间
        this.userService.updateLoginTimeByUsername(user.getUsername());
        return this.getLogin(user);
    }

    @GetMapping("/logout")
    public Result logout(final Principal user) {
        return ResultGenerator.genOkResult();
    }
    /**
     * 通讯录
     * */
    
    @PostMapping("/storeUser")
    public Result storeUser(){
    	List<Store> userlist=storeService.storeUser();
    	List<BaseRegion> response = new ArrayList<BaseRegion>();
    	for(Store bean: userlist) {
    		BaseRegion br = new BaseRegion();
    		br.setLabel(bean.getStoreName());
    		br.setValue(bean.getStoreName());
    		List<BaseStreet> children = new ArrayList<BaseStreet>();;
    		for(User user :bean.getUserlist()) {
    			BaseStreet bs = new BaseStreet();
    			bs.setLabel(user.getPhone()+"-"+user.getRelname()+"("+user.getUser_no()+")");
    			bs.setValue(user.getUsername());
    			children.add(bs);
    		}
    		br.setChildren(children);
    		response.add(br);
    	}
    	return ResultGenerator.genOkResult(response);
    }
    /**
     * 查看账户列表
     * @param map
     * @throws ParseException 
     * */
    @PreAuthorize("hasAuthority('user:list')")
    @PostMapping("/list")
    public Result findMyStoreUser(Principal user,@RequestBody  Map<String,Object> map) throws ParseException{
    	PageHelper.startPage(Integer.valueOf(map.get("page").toString()), Integer.valueOf(map.get("size").toString()));
    	if(user.toString().indexOf("ROLE_ADMIN")!=-1){//管理员 		
    		map.put("role", "1");
    	}else if(user.toString().indexOf("ROLE_SEC")!=-1){//助理
    		map.put("role", "3");
    	}else if(user.toString().indexOf("ROLE_LEADER")!=-1){//经理
    		map.put("role", "5");
    	}
    	List<CurrentUser> list=userService.findMyStoreUser(map);
    	for(CurrentUser suser:list){
    		//转换注册时间
    		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		Date date = fmt.parse(suser.getRegister_time());
    		String sre = fmt.format(date);
    		suser.setRegister_time(sre);
    	}
    	PageInfo<CurrentUser> pageInfo = new PageInfo<CurrentUser>(list);
    	return ResultGenerator.genOkResult(pageInfo);
    }
    
    /**
     * 冻结账户或解冻账户
     * 
     * */
    @PostMapping("/updateSign")
    public Result updateSign(@RequestBody  Map<String,Object> map){
    	if("0".equals(map.get("sign").toString())){
    		userService.updateSign(map);
    		return ResultGenerator.genOkResult("1");
    	}else if("1".equals(map.get("sign").toString())){
    		userService.updateSign(map);
    		return ResultGenerator.genOkResult("1");
    	}
    	return ResultGenerator.genOkResult("0");
    }
    
    /**
     * 查询切换账号
     * 
     * */
    @PostMapping("/UserCut")
    public Result UserCut(Principal user){
    	Map<String,Object> map=new HashMap<String,Object>();
    	map.put("username", user.getName());	
    	List<CurrentUser> list=userService.UserCut(map);   	
        return ResultGenerator.genOkResult(list);
    }
    /**
     * 切换账号
     * 
     * */
    @PostMapping("/changeAccount")
    public Result changeAccount(@RequestBody final User user){
    	 this.userService.updateLoginTimeByUsername(user.getUsername());
         return this.getLogin(user);
    }
    
    /**
     * 获得 token
     */
    private Result getToken(final User user) {
        final String username = user.getUsername();
        final UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
        final String token = this.jwtUtil.sign(username, userDetails.getAuthorities());
        return ResultGenerator.genOkResult(token);
    }
    
	private Result getLogin(final User user) {
        final String username = user.getUsername();
        final UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
        final String token = this.jwtUtil.sign(username, userDetails.getAuthorities());
        userService.updateTokenDB(username, token);
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        for(Object o : userDetails.getAuthorities()) {
        	SimpleGrantedAuthority sa = (SimpleGrantedAuthority)o;
        	if(sa.getAuthority().indexOf("_") != -1) {
        		response.setAuthorities(sa.getAuthority());
        	}
        	break;
        }
        return ResultGenerator.genOkResult(response);	 
    }
	
}
