package com.wangjiegulu.cleanandroidprojectmvp.provider.bll.inject.interactor;

import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.inject.application.ProviderApplicationComponent;
import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.inject.cache.ProviderUserCacheModule;
import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.inject.db.ProviderUserDatabaseModule;
import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.inject.file.ProviderUserFileModule;
import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.inject.net.ProviderUserNetworkModule;
import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.inject.prefs.ProviderUserPrefsModule;
import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.inject.scope.Provider_Scope_User;
import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.interactor.cache.CurrentLoginCache;
import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.interactor.impl.GithubInteractorImpl;
import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.interactor.impl.UserInteractorImpl;
import com.wangjiegulu.cleanandroidprojectmvp.provider.dal.db.dao.contract.UserDao;
import com.wangjiegulu.cleanandroidprojectmvp.provider.dal.file.FileAccessor;

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
