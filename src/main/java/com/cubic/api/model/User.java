package com.cubic.api.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author fei.yu
 * @date 2018/06/09
 */
@Table(name = "sys_user")
public class User {
    /**
     * 用户Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 邮箱
     */
//    @NotEmpty(message = "邮箱不能为空")
    @Email
    private String email;
    
    /**
     * 编号
     */
    @Column(name = "user_no")
    private String user_no;
    /**
     * 用户名
     */
    @NotEmpty(message = "用户名不能为空")
//    @Size(min = 3, message = "用户名长度不能小于3")
    private String username;

    /**
     * 密码
     */
    @JSONField(serialize = false)
//    @Size(min = 6, message = "密码长度不能小于6")
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

    public Long getStore_id() {
		return store_id;
	}

	public void setStore_id(Long store_id) {
		this.store_id = store_id;
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

	/**
     * 注册时间
     */
    @Column(name = "register_time")
    private Timestamp registerTime;

    /**
     * 上一次登录时间
     */
    @Column(name = "login_time")
    private Timestamp loginTime;

    /* ---------- 以下字段来自联表查询 ------------*/
    /**
     * 用户的角色Id
     */
    @Transient
    private Long roleId;

    /**
     * 用户的角色名
     */
    @Transient
    private String roleName;

    /**
     * 用户的角色对应的权限code
     */
    @Transient
    private List<String> permissionCodeList;


    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(final String avatar) {
        this.avatar = avatar;
    }

    public String getResume() {
        return this.resume;
    }

    public void setResume(final String resume) {
        this.resume = resume;
    }

    public Timestamp getRegisterTime() {
        return this.registerTime;
    }

    public void setRegisterTime(final Timestamp registerTime) {
        this.registerTime = registerTime;
    }

    public Long getRoleId() {
        return this.roleId;
    }

    public void setRoleId(final Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(final String roleName) {
        this.roleName = roleName;
    }

    public List<String> getPermissionCodeList() {
        return this.permissionCodeList;
    }

    public void setPermissionCodeList(final List<String> permissionCodeList) {
        this.permissionCodeList = permissionCodeList;
    }

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }

	public String getUser_no() {
		return user_no;
	}

	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}


    
    
}