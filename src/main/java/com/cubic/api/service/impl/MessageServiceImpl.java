package com.cubic.api.service.impl;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cubic.api.mapper.MessageMapper;
import com.cubic.api.model.SysMessage;
import com.cubic.api.service.MessageService;
import com.cubic.api.service.WebSocketServer;

/**
 * @author cubic
 * @date 2018/07/19
 */
@Service
@Transactional
public class MessageServiceImpl implements MessageService {

	@Resource
	private MessageMapper mapper;

	@Override
	public List<SysMessage> listMessage(String username) {
		List<SysMessage> list = mapper.listMessage(username);
		mapper.updateMessage(username);
		return list;
	}

	@Override
	public String getCount(String username) {
		return String.valueOf(mapper.getCount(username));
	}

	@Override
	public void updateCount(String username) {
		try {
			WebSocketServer.SendMessageByUserName(username, "0");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	 
	

}
