package com.ricard.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ricard.bean.Info;
import com.ricard.bean.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/delOrderServlet")
public class DelOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String order_id_str = request.getParameter("order_id");
        long order_id = Long.parseLong(order_id_str);

        // 获取session中的orders属性值
        HttpSession session = request.getSession();
        List<Order> orders = (List<Order>)session.getAttribute("orders");
        if(orders != null && orders.size() > 0) {
            for(Order order : orders) {
                if(order_id == order.getOrder_id()) {
                    orders.remove(order);
                    break;
                }
            }
        }
        session.setAttribute("orders", orders);
        ObjectMapper mapper = new ObjectMapper();
        Info info = new Info(true, "删除成功!");
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getWriter(), info);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
