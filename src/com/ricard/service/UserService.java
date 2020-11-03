package com.ricard.service;


import com.ricard.bean.User;

public interface UserService {
    User login(String username, String password);
}
