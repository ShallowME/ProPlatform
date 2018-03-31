package com.skyworth.service.websocket.message;

import com.skyworth.model.Message;
import com.skyworth.service.userService.UserService;
import com.skyworth.service.websocket.push.SpringWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author li
 * @date 2018/3/31 0031
 */
@Component
public class MessageGenerator {
    @Autowired
    private UserService userService;
    @Autowired
    private SpringWebSocketHandler webSocketHandler;

    /** 消息生成器
     * @param userId 个人用户id
     * @param companyId 企业用户id
     * @param mesContent 消息内容
     * @return 返回消息状态创建成功与否
     */
    public boolean generate(
            String username,
            Integer userId,
            Integer companyId,
            String mesContent) {
        Message message = new Message(null, userId, companyId, mesContent, -1, null);
        boolean pushMessage = webSocketHandler.pushMessage(username, mesContent);
        /*用户在线，推送成功，修改消息状态*/
        if (pushMessage) {
            message.setMesState(0);
        }
        /*保存消息*/
        return userService.saveMessage(message);
    }

}
