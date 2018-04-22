package com.skyworth.service.websocket.push;

import com.google.gson.Gson;
import com.skyworth.model.Message;
import com.skyworth.model.User;
import com.skyworth.service.userService.UserService;
import com.skyworth.service.websocket.vo.ContentVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.*;

/**
 * @author li
 */
@Component
public class SpringWebSocketHandler extends TextWebSocketHandler {


    private static Map<String,WebSocketSession> onlineUsers;
    private static Logger logger = Logger.getLogger(SpringWebSocketHandler.class);
    private Gson gson = new Gson();
    @Autowired
    private UserService userService;

    static {
        onlineUsers = new HashMap<>();
    }

    public SpringWebSocketHandler() {
        System.out.println("创建新的连接。");
    }

    /**
     * 连接成功时候，会触发页面上onOpen方法
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        String webSocketUser = (String) session.getAttributes().get("webSocketUser");
        onlineUsers.put(webSocketUser, session);
        /*测试*/
        onlineUsers.forEach((s, socketSession) -> System.out.println(s + socketSession));
        logger.info("connect to the webSocket success......当前数量:" + onlineUsers.size());

        //这块会实现自己业务，比如，当用户登录后，会把离线消息推送给用户
        User user = userService.getByUsername(webSocketUser);
        List<Message> offlineMessages = userService.getOfflineMessages(user.getId());
        if (offlineMessages == null) {
            return;
        }
        String msg = "您有新消息！";
        ContentVo vo = new ContentVo(null, msg, 1);
        TextMessage returnMessage = new TextMessage(gson.toJson(vo));
        try {
            if (session.isOpen()) {
                session.sendMessage(returnMessage);

                //修改离线消息状态为未读
                List<Integer> mesIds = new ArrayList<>();
                for (Message offlineMessage : offlineMessages) {
                    mesIds.add(offlineMessage.getId());
                }
                userService.modifyMessageState(mesIds,0);
            } else {
                logger.info("session closed");
            }
        } catch (Exception e) {
            logger.debug(e);
        }
    }

    /**
     * 关闭连接时触发
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) {
        logger.info("webSocket connection closed......");
        String username = (String) session.getAttributes().get("webSocketUser");
        logger.info("用户" + username + "已退出！");
        onlineUsers.remove("webSocketUser");
        onlineUsers.forEach((s, socketSession) -> System.out.println(s + socketSession));
    }


    /** 当前端js调用ws.send()方法时，该方法执行
     *
     * @param session   当前用户的连接会话
     * @param message   从客户端接收到的消息
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        System.out.println(message);
        sendMessageToOnlineUser(session, message);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        logger.info("webSocket connection closed......");
        onlineUsers.remove("webSocketUser");
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }


    /**
     * 给某个用户发送消息
     *
     * @param session   webSocket 连接通道
     * @param message   消息内容
     */
    private void sendMessageToOnlineUser(WebSocketSession session, TextMessage message) {
        System.out.println("sendMessageToOnlineUser..." + message.getPayload());
        try {
            if (session.isOpen()) {
                session.sendMessage(message);
            }
        } catch (IOException e) {
            logger.debug(e);
        }
    }

    /** 针对某个用户的订阅有相关的回馈
     * @param username  用户名
     * @param msg   消息
     */
    public boolean pushMessage(String username, String msg) {
        WebSocketSession socketSession = onlineUsers.get(username);
        if (socketSession != null) {
            msg = "您有新消息：" + msg;
            ContentVo contentVo = new ContentVo(null, msg, 1);
            TextMessage message = new TextMessage(gson.toJson(contentVo));
            try {
                socketSession.sendMessage(message);
                return true;
            } catch (IOException e) {
                logger.debug(e);
                return false;
            }
        }
        return false;
    }
    /**
     * 给所有在线用户发送消息
     *
     * @param message   广播群发的消息内容
     */
    public void sendMessageToUsers(TextMessage message) {
        for (WebSocketSession session : onlineUsers.values()) {
            try {
                if (session.isOpen()) {
                    session.sendMessage(message);
                }
            } catch (IOException e) {
                logger.debug(e);
            }
        }
    }
}
