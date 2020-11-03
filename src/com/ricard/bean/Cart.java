package com.ricard.bean;


/**
 * 购物车类
 */
public class Cart {
    private Long id;
    private String book_name;   // 书名
    private Integer count;      // 购买书的数量
    private Double sum;         // 总金额

    public Cart() {
    }

    public Cart(Long id, String book_name, Integer count, Double sum) {
        this.id = id;
        this.book_name = book_name;
        this.count = count;
        this.sum = sum;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
