package org.wanke.com.api;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wanke.com.entity.SysResources;
import org.wanke.com.mapper.SysMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/user")
@Api(value = "用户管理", description = "用户管理")
public class UserController extends BaseController{
    @Autowired
    SysMapper mapper;
    
    private static final Logger logger = LogManager.getLogger(UserController.class);

//    @RequestMapping(value = "/signup")
//    @PostMapping("/signup")
//    public SysUser signUp(@RequestBody SysUser user) {
//    	SysUser bizUser = userRepository.findByAccount(user.getAccount());
//        if(null != bizUser){
//            throw new UsernameIsExitedException("用户已经存在");
//        }
////        user.setPassword(DigestUtils.md5DigestAsHex((user.getPassword()).getBytes()));
//        user.setPassword("123456");
//        return userRepository.save(user);
//    }
    
    /**
     * 获取用户列表
     * @return
     */
    @ApiOperation(value = "查询用户列表")
    @GetMapping("/userList")
    public Map<String, Object> userList(){
        List<SysResources> resources = mapper.findAllSysResources();
        logger.info("resources: {}", resources);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("resources",resources);
        return map;
    }
    
    @GetMapping("/a")
    public String a(){
        return "a";
    }

    @ApiOperation(value = "查询用户权限")
    @GetMapping("/authorityList")
    public List<String> authorityList(){
//        List<String> authentication = getAuthentication();
//        return authentication;
    	return null;
    }
    
    public static void main(String[] args) {
    	Date date = new Date(System.currentTimeMillis() + 60 * 60 * 24 * 7000);
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
    	System.out.println(localDateTime);
    }
}



