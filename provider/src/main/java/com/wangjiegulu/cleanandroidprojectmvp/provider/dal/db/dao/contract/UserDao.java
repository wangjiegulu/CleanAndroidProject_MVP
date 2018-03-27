package com.wangjiegulu.cleanandroidprojectmvp.provider.dal.db.dao.contract;


import com.wangjiegulu.cleanandroidprojectmvp.provider.dal.db.dao.XBaseDao;
import com.wangjiegulu.cleanandroidprojectmvp.provider.dal.db.model.User;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 11/4/16.
 */
public interface UserDao extends XBaseDao<User> {

    User queryUser(long userId) throws Exception;
}
