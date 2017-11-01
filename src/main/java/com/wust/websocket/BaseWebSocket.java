package com.wust.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.logging.Logger;

/**
 * 发布一个名叫 basews 的websocket
 * 原生socket需要用 @ServerEndpoint 发布服务，
 * 使用 @OnOpen, @OnClose, @OnMessage , @OnError 等监听事件即可！
 */
@ServerEndpoint(value = "/basews")
@Component
public class BaseWebSocket {

	private Logger logger = Logger.getLogger(BaseWebSocket.class.getName());

	public BaseWebSocket() {
		logger.info("\n-----new BaseWebSocket-----\n");
	}

	// FIXME @ServerEndpoint比较特殊，无法注入单例的bean ?
	// @Autowired
	// SessionService sessionService;

    /**
      * @Author: F7687967
      * @Date: ${TIME} ${DATE}
      * @ModifiedBy:
      * @Description: 使用 @ServerEndpoint 发布服务
      * 每一个链接会新创建一个实例，所以完全可以把Session做为属性放在类里面。
      * 这个bean的作用域【应该】是prototype。
      * @ServerEndpoint 在里面注入其他的bean没有成功。增加了 @Scope（TARGET_CLASS）也不行。
      * 所以暂时用静态方法。
      */
	@OnOpen
	public void onOpen(Session session) {

		logger.info("sessionId:" + session.getId() + " > onOpen ");
		SessionService.newSession(session);
	}

	@OnClose
	public void onClose(Session session) {

		logger.info("sessionId:" + session.getId() + " > onClose ");
		SessionService.closeSession(session);
	}

	@OnMessage
	public void onMessage(String message, Session session) {
		//客户端发送Message到服务端,用日志打印在控制台
		logger.info("sessionId:" + session.getId() + " > OnMessage:" + message);

		//服务端发送消息到客户端
		SessionService.sendMessage(session, message);
	}

	@OnError
	public void onError(Session session, Throwable throwable) {
		logger.info("sessionId:" + session.getId() + " > OnError: " + throwable.getMessage());

		throwable.printStackTrace();

        SessionService.closeSession(session);
	}

}