package com.cubic.api.mapper;

import java.util.List;

import com.cubic.api.model.SysMessage;

/**
 * @author cubic
 * @date 2018/07/19
 */
public interface MessageMapper {
	List<SysMessage> listMessage(String username);
	int getCount(String username);
	void updateMessage(String username);
	void insertMessage(SysMessage message);
	List<String> listMessageUser(String username,String rolename);
}