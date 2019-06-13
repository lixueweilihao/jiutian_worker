package com.DesignPattern.gongchang.factory;

import com.DesignPattern.gongchang.dao.IUserDao;

public interface IDaoFactory {
    IUserDao createUserDao();
}
