package com.wangjiegulu.capmvp.provider.bll.inject.db;

import com.wangjiegulu.capmvp.provider.bll.inject.scope.Provider_Scope_User;
import com.wangjiegulu.capmvp.provider.dal.db.dao.contract.UserDao;
import com.wangjiegulu.capmvp.provider.dal.db.dao.contract.UserPreferenceDao;
import com.wangjiegulu.capmvp.provider.dal.db.dao.impl.UserDaoImpl;
import com.wangjiegulu.capmvp.provider.dal.db.dao.impl.UserPreferenceDaoImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Author: wangjie Email: tiantian.china.2@gmail.com Date: 11/7/16.
 */
@Module
public class ProviderUserDatabaseModule {

    @Provides
    @Provider_Scope_User
    public UserDao providerUserDao() {
        return new UserDaoImpl();
    }


    @Provides
    @Provider_Scope_User
    public UserPreferenceDao providerUserPreferenceDao() {
        return new UserPreferenceDaoImpl();
    }


}
