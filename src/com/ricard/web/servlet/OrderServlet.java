package com.ricard.web.servlet;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ricard.bean.Book;
import com.ricard.bean.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/orderServlet")
public class OrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取参数的json数据格式
        String books_str = request.getParameter("books");
        // 获取Session值
        HttpSession session = request.getSession();
        // 获取存储订单的集合
        List<Order> orders = (List)session.getAttribute("orders");
        if(orders == null) {
            orders = new ArrayList<>();
        }
        System.out.println(books_str);
        ObjectMapper mapper = new ObjectMapper();
        /**
         * JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, Book.class);
         *  List<Book> books = mapper.readValue(books_str, javaType);
         *  用于将json字符串转换为List<JavaBean>类型
         */
        JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, Book.class);
        // 将获取到的json数据转换为JavaBean(List)对象
        List<Book> books = mapper.readValue(books_str, javaType);

        /*List<String> book_names = (List<String>) session.getAttribute("book_names");
        if(book_names == null) {
            book_names = new ArrayList<String>();
        }*/
        double price = 0;
        for(Book book : books) {
            price += book.getPrice() * book.getCount();
            //book_names.add(book.getBook_name());
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date();
        String date_str = format.format(date);
        long order_id = date.getTime();
        Order order = new Order(price, date_str, order_id);
        orders.add(order);
        session.setAttribute("orders", orders);

        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getWriter(), order);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
