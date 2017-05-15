package com.csl.controller;

import com.csl.serviceImpl.SpringWebSocketHandler;
import com.csl.utility.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.TextMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by csl on 2017/5/12.
 */
@Controller
public class WebSocketController {
//    @Autowired
//    private SpringWebSocketHandler springWebSocketHandler;
//
//    @RequestMapping("/websocket/login")
//    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        String username = request.getParameter("username");
//        System.out.println(username + "登录");
//        HttpSession session = request.getSession(false);
//        session.setAttribute("SESSION_USERNAME", username);
//        //response.sendRedirect("/quicksand/jsp/websocket.jsp");
//        return new ModelAndView("websocket");
//    }
//
//    @RequestMapping("/websocket/send")
//    @ResponseBody
//    public String send(HttpServletRequest request) {
//        String username = request.getParameter("username");
//        this.springWebSocketHandler.sendMessageToUser(username, new TextMessage("你好，测试！！！！"));
//        return null;
//    }
}
