package com.ricard.bean;



public class Order {
    // 订单价格
    private double price;
    // 订单的时间
    private String date;
    // 订单号
    private long order_id;

    public Order() {
    }

    public Order(double price, String date, long order_id) {
        this.price = price;
        this.date = date;
        this.order_id = order_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }
}
