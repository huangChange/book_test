package com.ricard.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ricard.bean.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/cartOrderServlet")
public class CartOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取Session对象
        HttpSession session = request.getSession();
        // 获取所有订单
        List<Order> orders = (List<Order>) session.getAttribute("orders");
        if(orders == null) {
            orders = new ArrayList<Order>();
        }
        // 获取参数
        Double sum = Double.parseDouble(request.getParameter("sum"));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date();
        String date_str = format.format(date);
        long order_id = date.getTime();
        Order order = new Order(sum, date_str, order_id);
        orders.add(order);
        session.setAttribute("orders", orders);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
