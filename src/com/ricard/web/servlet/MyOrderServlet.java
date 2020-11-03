package com.ricard.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ricard.bean.Info;
import com.ricard.bean.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/myOrderServlet")
public class MyOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取当前session中的订单集合
        List<Order> orders = (List<Order>)request.getSession().getAttribute("orders");

        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        if(orders != null) {
            mapper.writeValue(response.getWriter(), orders);
        } else {
            mapper.writeValue(response.getWriter(), false);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
