package com.wangjiegulu.capmvp.provider.bll.inject.interactor;

import com.wangjiegulu.capmvp.provider.bll.inject.cache.ProviderUserCacheModule;
import com.wangjiegulu.capmvp.provider.bll.inject.db.ProviderUserDatabaseModule;
import com.wangjiegulu.capmvp.provider.bll.inject.file.ProviderUserFileModule;
import com.wangjiegulu.capmvp.provider.bll.inject.prefs.ProviderUserPrefsModule;
import com.wangjiegulu.capmvp.provider.bll.inject.scope.Provider_Scope_User;
import com.wangjiegulu.capmvp.provider.bll.interactor.cache.CurrentLoginCache;
import com.wangjiegulu.capmvp.provider.bll.interactor.impl.GithubInteractorImpl;
import com.wangjiegulu.capmvp.provider.bll.inject.application.ProviderApplicationComponent;
import com.wangjiegulu.capmvp.provider.bll.inject.net.ProviderUserNetworkModule;
import com.wangjiegulu.capmvp.provider.bll.interactor.impl.UserInteractorImpl;
import com.wangjiegulu.capmvp.provider.dal.db.dao.contract.UserDao;
import com.wangjiegulu.capmvp.provider.dal.file.FileAccessor;

import dagger.Component;

/**
 * Author: wangjie Email: tiantian.china.2@gmail.com Date: 11/7/16.
 */
@Component(dependencies = {ProviderApplicationComponent.class},
        modules = {
                ProviderUserNetworkModule.class,
                ProviderUserDatabaseModule.class,
                ProviderUserFileModule.class,
                ProviderUserPrefsModule.class,
                ProviderUserCacheModule.class
        })
@Provider_Scope_User
public interface ProviderUserInteractorComponent {

    UserDao providerUserDao();

//    UserPreferenceDao providerUserPreferenceDao();

    CurrentLoginCache providerCurrentLoginCache();

    FileAccessor providerFileAccessor();

    void inject(UserInteractorImpl interactor);

    void inject(GithubInteractorImpl interactor);
}
