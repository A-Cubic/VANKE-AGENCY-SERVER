package com.cubic.api.controller;

import java.security.Principal;
import java.util.List;

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
import com.cubic.api.model.LoginResponse;
import com.cubic.api.model.User;
import com.cubic.api.service.UserService;
import com.cubic.api.service.impl.UserDetailsServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author fei.yu
 * @date 2018/06/09
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private UserDetailsServiceImpl userDetailsService;
    @Resource
    private JwtUtil jwtUtil;

    @PostMapping
    public Result register(@RequestBody @Valid final User user,
                           final BindingResult bindingResult) {
        // {"username":"123456", "password":"123456", "email": "123456@qq.com"}
        if (bindingResult.hasErrors()) {
            //noinspection ConstantConditions
            final String msg = bindingResult.getFieldError().getDefaultMessage();
            return ResultGenerator.genFailedResult(msg);
        } else {
            this.userService.save(user);
            return this.getToken(user);
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

    @PreAuthorize("hasAuthority('user:list')")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") final Integer page,
                       @RequestParam(defaultValue = "0") final Integer size) {
        PageHelper.startPage(page, size);
        final List<User> list = this.userService.findAllUserWithRole();
        //noinspection unchecked
        final PageInfo pageInfo = new PageInfo(list);
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
        // 用户名登录
        User dbUser = null;
        if (user.getUsername() != null) {
            dbUser = this.userService.findBy("username", user.getUsername());
            if (dbUser == null) {
                return ResultGenerator.genFailedResult("账号不正确");
            }
        }
        // 邮箱登录
        if (user.getEmail() != null) {
            dbUser = this.userService.findBy("email", user.getEmail());
            if (dbUser == null) {
                return ResultGenerator.genFailedResult("错误的邮箱地址");
            }
            user.setUsername(dbUser.getUsername());
        }
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
