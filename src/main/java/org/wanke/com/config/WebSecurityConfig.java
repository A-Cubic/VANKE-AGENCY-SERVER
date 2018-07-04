package org.wanke.com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * SpringSecurity的配置
 * 通过SpringSecurity的配置，将JWTLoginFilter，JWTAuthenticationFilter组合在一起
 * @author fei.yu
 */
@Configuration
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	 /**
     * 需要放行的URL
     */
    private static final String[] AUTH_WHITELIST = {
//            "/user/signup"
    		"/login"
    };
	private UserDetailsService userDetailsService;
	
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;
 
//    public WebSecurityConfig(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
//        this.userDetailsService = userDetailsService;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	 http.cors().and().csrf().disable()
         .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
         .authorizeRequests()
         .antMatchers(AUTH_WHITELIST).permitAll()
         .anyRequest().authenticated()  // 所有请求需要身份认证
         .and()
         .addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class)
         .addFilter(new JWTLoginFilter(authenticationManager()))
         .addFilter(new JWTAuthenticationFilter(authenticationManager()))
         .logout() // 默认注销行为为logout，可以通过下面的方式来修改
         .logoutUrl("/logout")
         .logoutSuccessUrl("/login")
         .permitAll();// 设置注销成功后跳转页面，默认是跳转到登录页面;
    }
 
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//    	auth.authenticationProvider(new CustomAuthenticationProvider(userDetailsService,bCryptPasswordEncoder));
//    }
    
    @Bean
    UserDetailsService customUserService() { //注册UserDetailsService 的bean
        return new CustomUserService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService()); //user Details Service验证

    }
 
}
