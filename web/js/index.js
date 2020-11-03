
$(function() {

    // 注意这里的书籍对象需要与JavaBean中的对象和属性相同
    function Book(book_name, count, price){
        this.book_name = book_name;
        this.count = count;
        this.price = price;
    }

    // 点击查看我的购物车时
    $('.look').click(function() {
        $.get('/test_book/checkUserServlet', {}, function(user) {
            if(user.flag) {
                location.href = 'cart.html';
            } else {
                alert('您还未登录,请先登录!');
                location.href = 'login.html';
            }
        });
    });

    // 提交订单
    $('.btn').click(function() {
        $.get('/test_book/checkUserServlet', {}, function(user) {
            if(!user.flag) {
                alert('您还未登录,请登录!');
                location.href = 'login.html';
                return;
            }
            let counts = $('.count');       // 获取表单中每一个
            if(counts.length > 0) {
                let flag = true;
                var books = new Array();
                for(let i = 0; i < counts.length; i++) {
                    let count = counts[i].value;
                    if(count > 0) {
                        flag = false;
                        let inputs = $(counts[i]).parent().parent().children('input');
                        let book_name = inputs[0].value;
                        let price = inputs[1].value;
                        let book = new Book(book_name, count, price);
                        books.push(book);
                        console.log(books);
                    }
                }
                if(flag) {
                    alert('您没有选择任何商品!')
                } else {
                    if(confirm('是否支付?')) {
                        $.post('/test_book/orderServlet', {"books":JSON.stringify(books)}, function(data) {
                            console.log(data)
                            alert('尊敬的用户: ' + $('.login').html() + ',您一共消费: ' + data.price);
                            location.href = 'index.html';
                        });
                    }
                }
            } else {
                alert('书城为空!');
            }
        });
    });

    $('.delete').click(function() {
        // 获取当前点击元素的上三级父级元素
        // 将获取到的父级元素删除
        let obj = $(this).parent().parent();
        let parent = obj.parent();
        let book = obj.children('input')[0];
        let book_name = book.value;
        if(confirm("是否删除书: " + book_name + "?")) {
            parent.remove();
        }
    });

    // 当前元素点击时将当前书本+1
    $('.add').click(function() {
        var obj = $(this).prev();
        obj.val(parseInt(obj.val()) + 1);
    });

    // 当前元素点击时将当前书本-1
    $('.sub').click(function() {
        var obj = $(this).next();
        if(obj.val() > 0) {
            obj.val(parseInt(obj.val()) - 1);
        }
    });

    // 当点击加入购物车时
    $('.cart').click(function() {
        let that = this;
        $.get('/test_book/checkUserServlet', {}, function(user) {
            if(user.flag) {
                // 获取点击订单的父级元素的父级元素的子元素input
                let inputs = $(that).parent().parent().children('input');
                console.log(inputs);
                // 获取书的数量input对象
                let obj = $(that).parent().prev().children('input');
                if(obj.val() > 0) {
                    let book_name = inputs[0].value;    // 书名
                    let sum = inputs[1].value;          // 价格
                    $.get('/test_book/addCartServlet', {"book_name" : book_name, "count" : obj.val(), "sum" : sum}, function(info) {
                        if(info.flag) {
                            alert('加入购物车成功!');
                            location.href = "index.html";
                        }
                    });
                } else {
                    alert('您选择的商品数量为0!');
                }
            } else {
                alert('您还未登录,请先登录!');
                location.href = 'login.html';
            }
        });
    });

    $.get("/test_book/checkUserServlet", {}, function(user) {
        console.log(user);
        $('.login').html(user.message);
        if(user.flag) {
            $('.exit').html('退出登录');
            $('.order').html('我的订单');
        } else {
            $('.exit').html('登录');
        }
    });
    // 退出登录
    $('.exit').click(function() {
        if($('.exit').html() == '登录') {
            location.href = 'login.html';
        } else {
            if(confirm('是否退出?')) {
                $.get("/test_book/deleteUserServlet", {}, function(data) {
                    location.href = 'index.html';
                });
            }
        }
    });
})