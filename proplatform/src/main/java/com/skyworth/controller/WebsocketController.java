package com.skyworth.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author li
 * 测试
 */
@Controller
public class WebsocketController {
    private Logger logger = Logger.getLogger(WebsocketController.class);
    @RequestMapping("/websocket/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        logger.info(username+"登录");
        HttpSession session = request.getSession(true);
        session.setAttribute("username", username);
        return new ModelAndView("websocket");
    }

}
