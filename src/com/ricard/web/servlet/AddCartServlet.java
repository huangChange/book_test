package com.ricard.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ricard.bean.Cart;
import com.ricard.bean.Info;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/addCartServlet")
public class AddCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String book_name = request.getParameter("book_name");
        String count_str = request.getParameter("count");
        String sum = request.getParameter("sum");

        // 获取session对象
        HttpSession session = request.getSession();
        List<Cart> carts = (List<Cart>)session.getAttribute("carts");
        // 判断Session是否包含 List<Cart>对象
        if(carts == null) {
            carts = new ArrayList<Cart>();
        }

        if(book_name != null && count_str != null && sum != null) {
            int count = Integer.parseInt(count_str);
            Cart cart = new Cart(new Date().getTime(), book_name, count, Double.parseDouble(sum) * count);
            carts.add(cart);
        }
        session.setAttribute("carts", carts);

        Info info = new Info(true, "加入购物车成功!");
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getWriter(), info);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
