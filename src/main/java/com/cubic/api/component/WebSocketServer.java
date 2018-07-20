package com.cubic.api.component;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.cubic.api.service.MessageService;

/**
 * WebSocketServer服务端
 *
 * @author fei.yu
 * @date 2018/05/07
 */
@ServerEndpoint(value = "/vanke/com/ws/")
@Component
public class WebSocketServer {
	private static Logger log = LoggerFactory.getLogger(WebSocketServer.class);
	private static final AtomicInteger OnlineCount = new AtomicInteger(0);
	// concurrent包的线程安全Set，用来存放每个客户端对应的Session对象。
	private static CopyOnWriteArraySet<Session> SessionSet = new CopyOnWriteArraySet<Session>();
	private static Map<String, Session> UserMap = new ConcurrentHashMap<String, Session>();

	private MessageService service;

	private static ApplicationContext applicationContext;

	public static void setApplicationContext(ApplicationContext context) {
		applicationContext = context;
	}

	/**
	 * 连接建立成功调用的方法
	 */
	@OnOpen
	public void onOpen(Session session) {
		SessionSet.add(session);
		String username = session.getUserPrincipal().getName();
		UserMap.put(session.getUserPrincipal().getName(), session);
		int cnt = OnlineCount.incrementAndGet(); // 在线数加1
		log.info("有连接加入，当前连接数为：{}", cnt);
		try {
			SendMessageByUserName(username, this.getCount(username));
		} catch (IOException e) {
			log.error("发送消息错误：{}，user name: {}", e.getMessage(), username);
		}
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose(Session session) {
		for (String str : UserMap.keySet()) {
			if (session == UserMap.get(str)) {
				UserMap.remove(str);
			}
		}
		SessionSet.remove(session);

		int cnt = OnlineCount.decrementAndGet();
		log.info("有连接关闭，当前连接数为：{}", cnt);
	}

	/**
	 * 收到客户端消息后调用的方法
	 * 
	 * @param message
	 *            客户端发送过来的消息
	 */
	@OnMessage
	public void onMessage(String message, Session session) {
		log.info("来自客户端的消息：{}", message);
		// SendMessage(session, "收到消息，消息内容：" + message);

	}

	/**
	 * 出现错误
	 * 
	 * @param session
	 * @param error
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		log.error("发生错误：{}，Session ID： {}", error.getMessage(), session.getId());
	}

	/**
	 * 发送消息，实践表明，每次浏览器刷新，session会发生变化。
	 * 
	 * @param session
	 * @param message
	 */
	public static void SendMessage(Session session, String message) {
		try {
			session.getBasicRemote().sendText(message);
		} catch (IOException e) {
			log.error("发送消息出错：{}", e.getMessage());
		}
	}

	/**
	 * 群发消息
	 * 
	 * @param message
	 * @throws IOException
	 */
	public static void BroadCastInfo(String message) throws IOException {
		for (Session session : SessionSet) {
			if (session.isOpen()) {
				SendMessage(session, message);
			}
		}
	}

	/**
	 * 指定user发送消息
	 * 
	 * @param username
	 * @param message
	 * @throws IOException
	 */
	public static void SendMessageByUserName(String username, String message) throws IOException {
		// Session session = null;
		// for (Session s : SessionSet) {
		// if (s.getId().equals(sessionId)) {
		// session = s;
		// break;
		// }
		// }
		Session session = UserMap.get(username);
		if (session != null) {
			if (session.isOpen()) {
				SendMessage(session, message);
			}
		} else {
			log.warn("没有找到你指定会话：{}", username);
		}
	}
	
	private String getCount(String username) {
		service = applicationContext.getBean(MessageService.class);
		String msg = service.getCount(username);
		return msg;
	}
}
