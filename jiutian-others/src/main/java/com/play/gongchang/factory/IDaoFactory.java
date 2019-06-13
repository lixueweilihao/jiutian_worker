package com.play.gongchang.factory;


import com.play.gongchang.dao.IUserDao;

public interface IDaoFactory {
    IUserDao createUserDao();
}
