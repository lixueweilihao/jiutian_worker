package com.DesignPattern.gongchang.factory;

import com.DesignPattern.gongchang.dao.IUserDao;
import com.DesignPattern.gongchang.dao.MySQLUserDao;
import com.DesignPattern.gongchang.dao.PostgreSQLUserDao;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2019/3/18  9:44
 */
public class PostgreSQLDaoFactory implements IDaoFactory {

    @Override
    public IUserDao createUserDao() {
        return new PostgreSQLUserDao();
    }

}