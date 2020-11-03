package com.ricard.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ricard.bean.Info;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/checkUserServlet")
public class CheckUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String)request.getSession().getAttribute("username");
        ObjectMapper mapper = new ObjectMapper();
        Info info = new Info();
        if(username == null) {
            info.setFlag(false);
            info.setMessage("未登录");
        } else {
            info.setFlag(true);
            info.setMessage(username);
        }
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getWriter(), info);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
