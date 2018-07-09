package com.cubic.api.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cubic.api.core.service.WebSocketServer;

@RestController  
@RequestMapping("/api/ws")  
public class WebSocketController {
	
    /** 
     * 群发消息内容 
     * @param message 
     * @return 
     */  
	@RequestMapping(value="/sendAll", method=RequestMethod.GET)  
    String sendAllMessage(@RequestParam(required=true) String message,@RequestParam(required=true) String token){  
        try {  
            WebSocketServer.BroadCastInfo(message);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return "ok";  
    }  
    
    /** 
     * 指定会话ID发消息 
     * @param message 消息内容 
     * @param id 连接会话ID 
     * @return 
     */  
	@RequestMapping(value="/sendOne", method=RequestMethod.GET) 
    String sendOneMessage(@RequestParam(required=true) String message,@RequestParam(required=true) String id,@RequestParam(required=true) String token){  
        try {  
            WebSocketServer.SendMessage(id,message);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return "ok";  
    }  
}
