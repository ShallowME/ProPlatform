package com.skyworth.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skyworth.dto.MessageDto;
import com.skyworth.model.Message;
import com.skyworth.service.companyService.CompanyService;
import com.skyworth.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author li
 * @date 2018.3.21
 */
@RestController
public class MessageController {

    @Autowired
    private UserService userService;
    @Autowired
    private CompanyService companyService;

    @GetMapping("/user/message/show/{userId}/{messageStateCode}/{curPage}")
    public PageInfo<MessageDto> checkUserMessage(@PathVariable("userId") int userId,
                                                 @PathVariable("messageStateCode") int messageStateCode,
                                                 @PathVariable("curPage") int curPage) {
        /*设置页码和每页的记录数*/
        PageHelper.startPage(curPage, 5);
        /*查询操作*/
        List<Message> messages = userService.getMessageByMesState(userId, messageStateCode);
        List<MessageDto> result = new ArrayList<>();
        for (Message message : messages) {
            result.add(new MessageDto(message.getId(), message.getMesContent(), message.getModificationTime().getTime()));
        }
        // 查询之后，将结果集封装到PageInfo中，PageInfo其中包含会分页的详细信息和数据
        // 在构造方法中还可以设置页码导航的连续显示页码数，如显示3，4，5，6，7页按钮
        return new PageInfo<>(result, 1);
    }

    @PostMapping("/user/message/alter/{userId}/{messageId}")
    public String changeStateForUser(@RequestBody List<requestData> list) {
        return changeState(list,true);
    }



    @GetMapping("/company/message/show/{companyId}/{messageStateCode}/{curPage}")
    public PageInfo<MessageDto> checkCompanyMessage(@PathVariable("companyId") int companyId,
                                                    @PathVariable("messageStateCode") int messageStateCode,
                                                    @PathVariable("curPage")int curPage) {
        PageHelper.startPage(curPage, 5);
        List<Message> messages = companyService.getMessageByMesState(companyId, messageStateCode);
        List<MessageDto> result = new ArrayList<>();
        for (Message message : messages) {
            result.add(new MessageDto(message.getId(), message.getMesContent(), message.getModificationTime().getTime()));
        }
        return new PageInfo<>(result);
    }

    @PostMapping("/company/message/alter/{companyId}/{messageId}")
    public String changeStateForCompany(@RequestBody List<requestData> list) {
        return changeState(list, false);
    }


    /** 修改消息标记
     * @param list 封装了消息id及changeCode的对象集合
     * @param isUser    是否为个人用户
     * @return  成功"OK"，失败"Failure"
     */
    private String changeState(List<requestData> list,boolean isUser) {
        List<Integer> messageIds = new ArrayList<>();
        Integer changeCode = null;
        for (requestData requestData : list) {
            messageIds.add(requestData.getMessageId());
            if (changeCode == null) {
                changeCode = requestData.getChangeCode();
            }
        }
        boolean b;
        if (isUser) {
            b = userService.modifyMessageState(messageIds, changeCode);
        } else {
            b = companyService.modifyMessageState(messageIds, changeCode);
        }
        return b ? "OK" : "Failure";
    }

    @PostMapping("/message/delete")
    public String deleteMessage(@RequestBody List<requestData> requestDataList) {
        if (requestDataList == null || requestDataList.size() == 0) {
            return "Failure";
        }
        List<Integer> ids = new ArrayList<>();
        for (requestData data : requestDataList) {
            ids.add(data.getMessageId());
        }
        boolean b = userService.removeMessageByBatch(ids);
        if (b) {
            return "OK";
        }
        return "Failure";
    }

}

class requestData {
    private int userId;
    private int messageId;
    private int changeCode;

    public requestData() {
    }

    public requestData(int userId, int messageId, int changeCode) {
        this.userId = userId;
        this.messageId = messageId;
        this.changeCode = changeCode;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getChangeCode() {
        return changeCode;
    }

    public void setChangeCode(int changeCode) {
        this.changeCode = changeCode;
    }

    @Override
    public String toString() {
        return "requestData{" +
                "userId=" + userId +
                ", messageId=" + messageId +
                ", changeCode=" + changeCode +
                '}';
    }
}