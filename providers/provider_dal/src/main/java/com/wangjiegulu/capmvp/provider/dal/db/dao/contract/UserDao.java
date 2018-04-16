package com.wangjiegulu.capmvp.provider.dal.db.dao.contract;


import com.wangjiegulu.capmvp.provider.dal.db.dao.XBaseDao;
import com.wangjiegulu.capmvp.provider.dal.db.po.User;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 11/4/16.
 */
public interface UserDao extends XBaseDao<User> {

    User queryUser(long userId) throws Exception;
}
