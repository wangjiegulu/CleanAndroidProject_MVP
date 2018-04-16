package com.wangjiegulu.capmvp.inject.app;


import com.wangjiegulu.capmvp.application.CAPApplication;
import com.wangjiegulu.capmvp.inject.scope.Scope_Application;
import com.wangjiegulu.capmvp.provider.bll.interactor.contract.GlobalInteractor;
import com.wangjiegulu.capmvp.provider.bll.interactor.impl.GlobalInteractorImpl;

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
