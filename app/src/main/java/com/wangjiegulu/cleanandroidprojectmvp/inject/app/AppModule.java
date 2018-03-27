package com.wangjiegulu.cleanandroidprojectmvp.inject.app;


import com.wangjiegulu.cleanandroidprojectmvp.application.CAPApplication;
import com.wangjiegulu.cleanandroidprojectmvp.inject.scope.Scope_Application;
import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.interactor.contract.GlobalInteractor;
import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.interactor.impl.GlobalInteractorImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 10/19/16.
 */
@Module
public class AppModule {
    private CAPApplication application;

    public AppModule(CAPApplication application) {
        this.application = application;
    }

    @Provides
    @Scope_Application
    GlobalInteractor providerGlobalInteractor() {
        return new GlobalInteractorImpl();
    }

}
