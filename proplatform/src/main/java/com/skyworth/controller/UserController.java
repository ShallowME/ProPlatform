package com.skyworth.controller;

import com.alibaba.fastjson.JSONObject;
import com.skyworth.dto.UserDto;
import com.skyworth.model.User;
import com.skyworth.service.userService.UserService;
import com.skyworth.utils.ConstantHolder;
import com.skyworth.utils.shiro.session.CustomSessionDAO;
import com.skyworth.utils.sms.JsonSmsSender;
import com.skyworth.utils.sms.SmsSender;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Shallow on 2018/3/6.
 */

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private CustomSessionDAO customSessionDAO;

    @Resource
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Map<String, Object> userRegister(HttpServletRequest request){
        Map<String,Object> resultMap = new HashMap<>();
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("rePassword");
        String phoneNum = request.getParameter("phoneNum");
        String idCode = request.getParameter("idCode");

        if (userService.checkUserExist(userName)){
            resultMap.put("isSuccessful",false);
            resultMap.put("errorMessage","该用户已存在，请直接登录。");
        }
        else if (codeTimeOut()){
            resultMap.put("isSuccessful",false);
            resultMap.put("errorMessage","验证码已超时，请重新获取。");
        }
        else {
            Session session = SecurityUtils.getSubject().getSession();
            String code = (String) session.getAttribute(ConstantHolder.SMS_VALIDATE_CODE);
            if (idCode.equals(code) && password.equals(rePassword)){
                resultMap.put("isSuccessful",true);
                resultMap.put("message","注册成功。");
                UserDto userDto = new UserDto(userName,phoneNum,password);
                userService.register(userDto);
            }
        }
        return resultMap;
    }

    @RequestMapping(value = "/login/{identity}/{password}",method = RequestMethod.GET)
    public Map<String,Object> userLogin(@PathVariable("identity")String identity,@PathVariable("password")String password, HttpServletRequest request){
        Map<String,Object> resultMap = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        User userLogin = Optional.ofNullable(userService.getByUsername(identity)).orElse(userService.getByPhoneNum(identity));
        if (userLogin == null){
            resultMap.put("isSuccessful",false);
            resultMap.put("errorMessage","该用户不存在，请先注册。");
            resultMap.put("userId",0);
            resultMap.put("userName",null);
        }else {
            String host = request.getRemoteHost();
            UsernamePasswordToken loginToken = new UsernamePasswordToken(userLogin.getUserName(), userLogin.getUserPassword(), host);
            subject.login(loginToken);
            if (subject.isAuthenticated()) {
                resultMap.put("isSuccessful", true);
                resultMap.put("errorMessage", null);
                resultMap.put("userId", userLogin.getId());
                resultMap.put("userName", userLogin.getUserName());
            }
        }

        return resultMap;
    }

    @RequestMapping(value = "/idCodeConfirm/{idCode}",method = RequestMethod.GET)
    public Map<String, Boolean> companyIdCodeConfirm(@PathVariable("idCode") String idCode){

        Session userSession = SecurityUtils.getSubject().getSession();
        Session cacheSession = customSessionDAO.readSession(userSession.getId());
        Map<String,Boolean> resultMap = new HashMap<>();
        String code = (String) cacheSession.getAttribute(ConstantHolder.SMS_VALIDATE_CODE);

        resultMap.put("isSuccessful",idCode.equalsIgnoreCase(idCode));
        return resultMap;
    }


    @RequestMapping(value = "/forget",method = RequestMethod.POST)
    public ResponseEntity<?> userForgetPasswordAlter( HttpServletRequest request){
        String phoneNum = request.getParameter("phoneNum");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("rePassword");

        assert !password.equals(rePassword);

        userService.updatePasswordByPhoneNum(phoneNum,password);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/alter/{userId}",method = RequestMethod.POST)
    public Map<String, Object> userCommonPasswordAlter(@PathVariable("userId") int userId, HttpServletRequest request){
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String rePassword = request.getParameter("rePassword");

        Map<String, Object> resultMap = new HashMap<>();

        User currentUser = userService.getByUserId(userId);
        if (!currentUser.getUserPassword().equals(oldPassword)){
            resultMap.put("isSuccessful", false);
            resultMap.put("message","密码错误");
        }
        else if (!newPassword.equals(rePassword)){
            resultMap.put("isSuccessful", false);
            resultMap.put("message","两次输入的密码不一致");
        }
        else {
            userService.updatePasswordById(userId, newPassword);
            resultMap.put("isSuccessful", true);
            resultMap.put("message","修改成功");
        }
        return resultMap;
    }

    private boolean codeTimeOut(){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Session session = SecurityUtils.getSubject().getSession();
        Session cacheSession = customSessionDAO.readSession(session.getId());

        String createTime = (String) cacheSession.getAttribute(ConstantHolder.SMS_VALIDATE_TIME);


        try {
            Date createDate = sdf.parse(createTime);
            Date validateDate = new Date(System.currentTimeMillis());
            long interval = (validateDate.getTime() - createDate.getTime())/1000;
            return interval > 90;
        } catch (ParseException e) {
            logger.error("Created time parsing error.", e);
        }
        return false;
    }



}
