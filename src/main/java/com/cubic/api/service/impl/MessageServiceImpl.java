package com.cubic.api.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cubic.api.component.WebSocketServer;
import com.cubic.api.mapper.MessageMapper;
import com.cubic.api.model.SysMessage;
import com.cubic.api.service.MessageService;

/**
 * @author cubic
 * @date 2018/07/19
 */
@Service
@Transactional(rollbackFor = Exception.class)
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

	@Override
	public void sendMessage(String type,String content,String url,String username) {
		String rolename = "ROLE_MANAGER";
		if("2".equals(type)) { //type:1.店长审核，2.助理审核
			rolename = "ROLE_SEC";
		}
		List<String> result = mapper.listMessageUser(username,rolename);
		if(result != null && result.size()>0) {
			for(String str : result) {
				try {
					SysMessage message = new SysMessage();
					message.setContent(content);
					message.setUrl(url);
					message.setSender(username);
					message.setReceiver(str);
					mapper.insertMessage(message);
					WebSocketServer.SendMessageByUserName(str, getCount(str));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public void sendMessageResult(String content,String url,String username,String receiver) {
		SysMessage message = new SysMessage();
		message.setContent(content);
		message.setUrl(url);
		message.setSender(username);
		message.setReceiver(receiver);
		mapper.insertMessage(message);
		try {
			WebSocketServer.SendMessageByUserName(username, getCount(username));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sendMessageSystem(List<String> houseWarnList,List<String> houseShareList, List<String> guestWarnList, List<String> guestShareList) {
		Map<String,String> map = new HashMap<String,String>();
		for(String str : houseWarnList) {
			SysMessage message = new SysMessage();
			message.setContent("您的房源已有10天未跟进，系统将于5天后，把该房源置入共享池内");
			message.setUrl("#");
			message.setReceiver(str);
			mapper.insertMessage(message);
			map.put(str, str);
		}
		
		for(String str : houseShareList) {
			SysMessage message = new SysMessage();
			message.setContent("有新的房源置入共享池中");
			message.setUrl("#");
			message.setReceiver(str);
			mapper.insertMessage(message);
			map.put(str, str);
		}
		
		for(String str : guestWarnList) {
			SysMessage message = new SysMessage();
			message.setContent("您的客源已有10天未跟进，系统将于5天后，把该客源置入共享池内。");
			message.setUrl("#");
			message.setReceiver(str);
			mapper.insertMessage(message);
			map.put(str, str);
		}
		
		for(String str : guestShareList) {
			SysMessage message = new SysMessage();
			message.setContent("有新的客源置入共享池中");
			message.setUrl("#");
			message.setReceiver(str);
			mapper.insertMessage(message);
			map.put(str, str);
		}
		
		for(String str : map.keySet()) {
			try {
				WebSocketServer.SendMessageByUserName(str, getCount(str));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	 
	

}
