package com.ricard.service.impl;


import com.ricard.bean.User;
import com.ricard.dao.UserDao;
import com.ricard.dao.impl.UserDaoImpl;
import com.ricard.service.UserService;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();

    @Override
    public User login(String username, String password) {
        return userDao.findByUsernameAndPassword(username, password);
    }
}
