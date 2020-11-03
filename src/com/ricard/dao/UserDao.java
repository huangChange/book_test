package com.ricard.dao;

import com.ricard.bean.User;

public interface UserDao {
    User findByUsernameAndPassword(String username, String password);
}
