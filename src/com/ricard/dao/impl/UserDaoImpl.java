package com.ricard.dao.impl;


import com.ricard.bean.User;
import com.ricard.dao.UserDao;
import com.ricard.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDs());

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        String sql = "select * from teacher where username = ? and password = ?";

        try {
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),
                    username, password);

            return user;
        } catch (DataAccessException e) {
            return null;
        }
    }
}
