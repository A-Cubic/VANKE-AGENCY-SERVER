package com.cubic.api.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cubic.api.core.response.Result;
import com.cubic.api.core.response.ResultGenerator;
import com.cubic.api.model.SysMessage;
import com.cubic.api.service.MessageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/vanke/message")
public class MessageController {
	@Resource
    private MessageService service;
	
	/**
	 * 根据用户 读取消息列表
	 * 
	 * @return
	 */
	@PostMapping("/list")
	public Result listMessage(Principal user,@RequestBody Map<String,Object> map) {
		PageHelper.startPage(Integer.valueOf(map.get("page").toString()), Integer.valueOf( map.get("size").toString()));
		List<SysMessage> list = service.listMessage(user.getName());
		service.updateCount(user.getName());
		PageInfo<SysMessage> pageInfo = new PageInfo<SysMessage>(list);
		return ResultGenerator.genOkResult(pageInfo);
	}

	/**
	 * 群发消息内容
	 * 
	 * @param message
	 * @return
	 */
	@RequestMapping(value = "/sendAll", method = RequestMethod.GET)
	String sendAllMessage(@RequestParam(required = true) String message) {
//		try {
//			WebSocketServer.BroadCastInfo(message);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		return "ok";
	}

	/**
	 * 指定会话ID发消息
	 * 
	 * @param message
	 *            消息内容
	 * @param id
	 *            连接会话ID
	 * @return
	 */
	@RequestMapping(value = "/sendOne", method = RequestMethod.GET)
	String sendOneMessage(@RequestParam(required = true) String message, @RequestParam(required = true) String id) {
//		try {
//			WebSocketServer.SendMessageByUserName(id, message);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		return "ok";
	}
}
