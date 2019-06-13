package com.DesignPattern.gongchang.client;

import com.DesignPattern.gongchang.dao.IUserDao;
import com.DesignPattern.gongchang.factory.IDaoFactory;
import com.DesignPattern.gongchang.factory.MySQLDaoFactory;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2019/3/18  9:39
 */
public class Client {

    public static void main(String[] args) {
        IDaoFactory factory = new MySQLDaoFactory();
        IUserDao userDao = factory.createUserDao();
        userDao.getUser("admin");

    }

}