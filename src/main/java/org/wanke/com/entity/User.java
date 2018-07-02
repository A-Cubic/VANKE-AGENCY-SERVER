package org.wanke.com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "sys_user")
public class User {
	@Id
    @GeneratedValue
	private long id;
	private String account;
	private String password;
	private String user_name;
	private String phone;
	private String sex;
	private long store_id;
	private String enable;
	private String type;
	private String head_url;
	private String create_time;
	private long create_id;
	private String update_time;
	private long update_id;
	private String del_flag;
	private String del_time;
	private long del_id;
}
