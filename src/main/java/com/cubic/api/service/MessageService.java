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
	//发起审核通知
	void sendMessage(String type,String content,String url,String username);
	//系统通知
	void sendMessageSystem(List<String> houseWarnList,List<String> houseShareList, List<String> guestWarnList, List<String> guestShareList);
	//审核结果通知
	void sendMessageResult(String content,String url,String username,String receiver);
}
