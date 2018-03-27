package com.wangjiegulu.cleanandroidprojectmvp.provider.bll.inject.application;


import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.inject.prefs.ProviderApplicationPrefsModule;
import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.inject.scope.Provider_Scope_Application;
import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.interactor.impl.GlobalInteractorImpl;
import com.wangjiegulu.cleanandroidprojectmvp.provider.dal.prefs.PrefsConstants;
import com.wangjiegulu.cleanandroidprojectmvp.provider.dal.prefs.PrefsHelper;

import javax.inject.Named;

import dagger.Component;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 11/14/16.
 */
@Component(modules = {ProviderApplicationPrefsModule.class})
@Provider_Scope_Application
public interface ProviderApplicationComponent {
    @Named(PrefsConstants.PREFS_GLOBAL)
    PrefsHelper providerGlobalPrefsHelper();

    void inject(GlobalInteractorImpl interactor);

}
