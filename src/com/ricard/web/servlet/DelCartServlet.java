package com.ricard.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ricard.bean.Cart;
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

@WebServlet("/delCartServlet")
public class DelCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id_str = request.getParameter("id");
        long id = Long.parseLong(id_str);

        // 获取session中的orders属性值
        HttpSession session = request.getSession();
        List<Cart> carts = (List<Cart>)session.getAttribute("carts");
        if(carts != null && carts.size() > 0) {
            for(Cart cart : carts) {
                if(id == cart.getId()) {
                    carts.remove(cart);
                    break;
                }
            }
        }
        session.setAttribute("carts", carts);
        ObjectMapper mapper = new ObjectMapper();
        Info info = new Info(true, "删除成功!");
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getWriter(), info);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
