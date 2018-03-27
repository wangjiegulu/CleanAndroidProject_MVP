package com.wangjiegulu.cleanandroidprojectmvp.inject.app;


import com.wangjiegulu.cleanandroidprojectmvp.application.CAPApplication;
import com.wangjiegulu.cleanandroidprojectmvp.inject.scope.Scope_Application;
import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.interactor.contract.GlobalInteractor;

import dagger.Component;

/**
 * Created by wangjie on 10/19/16.
 */
@Component(modules = AppModule.class)
@Scope_Application
public interface AppComponent {

    void inject(CAPApplication application);

    GlobalInteractor providerGlobalInteractor();

//    @Named(PrefsService.GLOBAL)
//    PrefsService providerGlobalPrefsService();
}
