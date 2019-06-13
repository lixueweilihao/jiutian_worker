package com.play.gongchang.dao;

import com.play.gongchang.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2019/3/18  9:40
 */
public class MySQLUserDao implements IUserDao {

     Logger LOG = LoggerFactory.getLogger(MySQLUserDao.class);

    @Override
    public void addUser(User user) {
        LOG.info("MySQL added User {}", user);
    }

    @Override
    public void removeUser(User user) {
        LOG.info("MySQL removed User {}", user);
    }

    @Override
    public User getUser(String username) {
        User user = new User();
        user.setUsername(username);
        return user;
    }

}
