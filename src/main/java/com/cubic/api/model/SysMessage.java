package com.cubic.api.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SysMessage {
	private Long id;
	private String content;
	private String url;
	private String sender="system";
	private String receiver;
	private String create_time;
	private String last_time;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getCreate_time() {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date date;
	    String re = "";
		try {
			date = fmt.parse(create_time);
			re = fmt.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return re;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getLast_time() {
		return last_time;
	}
	public void setLast_time(String last_time) {
		this.last_time = last_time;
	}
	
}
