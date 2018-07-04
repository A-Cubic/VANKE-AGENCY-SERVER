package org.wanke.com.entity;

import lombok.Data;

@Data
public class SysRole {
	private long id;
	private String role_name;
	private String role_desc;
	private String create_time;
	private long create_id;
	private String update_time;
	private long update_id;
}
