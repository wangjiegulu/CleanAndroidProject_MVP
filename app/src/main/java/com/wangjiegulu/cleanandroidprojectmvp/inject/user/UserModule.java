package com.wangjiegulu.cleanandroidprojectmvp.inject.user;

import com.wangjiegulu.cleanandroidprojectmvp.application.CAPApplication;

import dagger.Module;

/**
 * Created by wangjie on 10/19/16.
 */
@Module
public class UserModule {
    private CAPApplication application;

    public UserModule(CAPApplication application) {
        this.application = application;
    }

//    @Provides
//    @Scope_User
//    public DatabaseService providerDatabaseService() {
//        return new DatabaseService();
//    }
//
//    @Provides
//    @Scope_User
//    @Named(PrefsService.USER)
//    public PrefsService providerUserPrefsService() {
//        return new PrefsService(PrefsService.USER);
//    }
//
//    @Provides
//    @Scope_User
//    public NetworkService providerNetworkService() {
//        return new NetworkService();
//    }
}
