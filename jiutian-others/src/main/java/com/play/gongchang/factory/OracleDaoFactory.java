package com.play.gongchang.factory;

import com.play.gongchang.dao.IUserDao;
import com.play.gongchang.dao.OracleUserDao;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2019/3/18  9:44
 */
public class OracleDaoFactory implements IDaoFactory {

    @Override
    public IUserDao createUserDao() {
        return new OracleUserDao();
    }

}
