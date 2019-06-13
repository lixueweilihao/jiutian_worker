package com.DesignPattern.gongchang.dao;

import com.DesignPattern.gongchang.bean.User;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2019/3/18  9:40
 */
public interface IUserDao {
    void addUser(User user);
    void removeUser(User user);
    User getUser(String username);
}
