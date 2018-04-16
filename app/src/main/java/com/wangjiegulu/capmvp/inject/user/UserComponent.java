package com.wangjiegulu.capmvp.inject.user;


import com.wangjiegulu.capmvp.provider.bll.interactor.contract.GithubInteractor;
import com.wangjiegulu.capmvp.provider.bll.interactor.contract.UserInteractor;
import com.wangjiegulu.capmvp.inject.app.AppComponent;
import com.wangjiegulu.capmvp.inject.modules.InteractorModule;
import com.wangjiegulu.capmvp.inject.scope.Scope_User;

import dagger.Component;

/**
 * Created by wangjie on 10/19/16.
 */
@Component(dependencies = AppComponent.class, modules = {UserModule.class, InteractorModule.class})
@Scope_User
public interface UserComponent {
    // From AppComponent
//    GlobalInteractor providerGlobalPrefsInteractor();

//    DatabaseService providerDatabaseService();
//    @Named(PrefsService.USER)
//    PrefsService providerUserPrefsService();


    UserInteractor providerUserInteractor();

    GithubInteractor providerGithubInteractor();


}
