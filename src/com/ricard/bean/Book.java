package com.ricard.bean;



public class Book {
    private String book_name;
    private Double price;
    private Integer count;

    public Book() {
    }

    public Book(String book_name, Double price, Integer count) {
        this.book_name = book_name;
        this.price = price;
        this.count = count;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
