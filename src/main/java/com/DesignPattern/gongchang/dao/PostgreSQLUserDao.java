package com.DesignPattern.gongchang.dao;

import com.DesignPattern.gongchang.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2019/3/18  9:41
 */
public class PostgreSQLUserDao implements IUserDao {

    private static final Logger LOG = LoggerFactory.getLogger(PostgreSQLUserDao.class);

    @Override
    public void addUser(User user) {
        LOG.info("PostgreSQL added User {}", user);
    }

    @Override
    public void removeUser(User user) {
        LOG.info("PostgreSQL removed User {}", user);
    }

    @Override
    public User getUser(String username) {
        User user = new User();
        user.setUsername(username);
        return user;
    }
}
