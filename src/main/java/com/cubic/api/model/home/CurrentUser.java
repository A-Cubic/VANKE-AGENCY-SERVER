package com.cubic.api.model.home;

import java.util.Date;

import javax.persistence.Column;

import com.alibaba.fastjson.annotation.JSONField;

public class CurrentUser {
	 /**
     * 用户Id
     */
    private Long id;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    @JSONField(serialize = false)
    private String password;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 简介
     */
    private String resume;
    
    /**
     * 门店id
     */
    private Long store_id;
    
    /**
     * 门店名
     */
    private String store_name;
    
    /**
     * 电话
     */
    private String phone;
    
    /**
     * 性别
     */
    private String sex;
    
    /**
     * 真实姓名
     */
    private String relname;
    
    /**
     * 当前业绩
     */
    private String current_score;
    
    /**
     * 潜在业绩
     */
    private String latent_score;
    
    /**
     * 用户编号
     */
    private String user_no;
    
    /**
     * 角色名
     */
    private String desc;
    
    /**
     * 注册时间
     */
    private String register_time;

    
    
	public String getRegister_time() {
		return register_time;
	}

	public void setRegister_time(String register_time) {
		this.register_time = register_time;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getUser_no() {
		return user_no;
	}

	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public Long getStore_id() {
		return store_id;
	}

	public void setStore_id(Long store_id) {
		this.store_id = store_id;
	}

	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getRelname() {
		return relname;
	}

	public void setRelname(String relname) {
		this.relname = relname;
	}

	public String getCurrent_score() {
		return current_score;
	}

	public void setCurrent_score(String current_score) {
		this.current_score = current_score;
	}

	public String getLatent_score() {
		return latent_score;
	}

	public void setLatent_score(String latent_score) {
		this.latent_score = latent_score;
	}
    
    
}
