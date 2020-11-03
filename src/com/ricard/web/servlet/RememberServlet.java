package com.ricard.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ricard.bean.CookieBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/rememberServlet")
public class RememberServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 先获取cookie
        Cookie[] cookies = request.getCookies();
        String cookie_username = "";
        String cookie_password = "";
        if(cookies != null) {
            for(Cookie cookie : cookies) {
                if(cookie.getName().equals("username")) {
                    cookie_username = cookie.getValue();
                }
                if(cookie.getName().equals("password")) {
                    cookie_password = cookie.getValue();
                }
            }
        }

        /*System.out.println("记住密码!");
        System.out.println(cookie_username);
        System.out.println(cookie_password);*/

        if(!cookie_username.equals("") && !cookie_password.equals("")) {
            ObjectMapper objectMapper = new ObjectMapper();

            CookieBean cookieBean = new CookieBean();
            cookieBean.setUsername(cookie_username);
            cookieBean.setPassword(cookie_password);
            response.setContentType("application/json;charset=utf-8");
            objectMapper.writeValue(response.getWriter(), cookieBean);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
