package com.ricard.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ricard.bean.Info;
import com.ricard.bean.User;
import com.ricard.service.UserService;
import com.ricard.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rpassword = request.getParameter("rpassword");

        UserService userService = new UserServiceImpl();
        User user = userService.login(username, password);
        Info info = new Info();
        if(user != null) {
            info.setFlag(true);
            info.setMessage("");
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            if(rpassword != null) {
                // 创建一个Cookie对象,用来存储用户名
                Cookie cookie1 = new Cookie("username", username);
                // 创建一个cookie对象,用于存储用户的密码
                Cookie cookie2 = new Cookie("password", password);

                cookie1.setMaxAge(60 * 10);
                cookie2.setMaxAge(60 * 10);
                // 发送cookie
                response.addCookie(cookie1);
                response.addCookie(cookie2);
            } else {
                Cookie[] cookies = request.getCookies();
                if(cookies != null) {
                    for(Cookie cookie : cookies) {
                        if(cookie.getName().equals("username") || cookie.getName().equals("password")) {
                            cookie.setMaxAge(0);
                            response.addCookie(cookie);
                        }
                    }
                }
            }
        } else {
            info.setFlag(false);
            info.setMessage("用户名或密码错误");
        }
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getWriter(), info);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
