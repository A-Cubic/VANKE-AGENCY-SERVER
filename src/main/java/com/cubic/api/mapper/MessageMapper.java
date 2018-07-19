package com.cubic.api.mapper;

import java.util.List;

import com.cubic.api.model.SysMessage;

public interface MessageMapper {
	List<SysMessage> listMessage(String username);
	int getCount(String username);
	void updateMessage(String username);
}