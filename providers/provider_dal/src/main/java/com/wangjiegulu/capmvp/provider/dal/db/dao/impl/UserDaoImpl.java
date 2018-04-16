package com.wangjiegulu.capmvp.provider.dal.db.dao.impl;

import com.wangjiegulu.capmvp.provider.dal.db.dao.XBaseDaoImpl;
import com.wangjiegulu.capmvp.provider.dal.db.dao.contract.UserDao;
import com.wangjiegulu.capmvp.provider.dal.db.po.User;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 11/4/16.
 */
public class UserDaoImpl extends XBaseDaoImpl<User> implements UserDao {
    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    public User queryUser(long userId) throws Exception {
        // TODO: 02/08/2017 wangjie
        return null;
    }
}
