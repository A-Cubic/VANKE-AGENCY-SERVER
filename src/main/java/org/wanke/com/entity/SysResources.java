package org.wanke.com.entity;

import lombok.Data;

@Data
public class SysResources {
	private long id;
	private String name;
	private String res_url;
	private String method;
	private long parent_id;
	private String sort;
	private String create_time;
	private long create_id;
	private String update_time;
	private long update_id;
}
