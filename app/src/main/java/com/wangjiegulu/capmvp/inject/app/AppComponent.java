package com.wangjiegulu.capmvp.inject.app;


import com.wangjiegulu.capmvp.application.CAPApplication;
import com.wangjiegulu.capmvp.provider.bll.interactor.contract.GlobalInteractor;
import com.wangjiegulu.capmvp.inject.scope.Scope_Application;

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
