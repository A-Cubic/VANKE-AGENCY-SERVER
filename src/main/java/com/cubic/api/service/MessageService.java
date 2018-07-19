package com.cubic.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cubic.api.model.SysMessage;

/**
 * @author cubic
 * @date 2018/07/19
 */
@Service
public interface MessageService {
	List<SysMessage> listMessage(String username);
	void updateCount(String username);
	String getCount(String username);
}
