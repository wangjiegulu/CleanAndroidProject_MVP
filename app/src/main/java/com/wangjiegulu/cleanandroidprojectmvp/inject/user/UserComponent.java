package com.wangjiegulu.cleanandroidprojectmvp.inject.user;


import com.wangjiegulu.cleanandroidprojectmvp.inject.app.AppComponent;
import com.wangjiegulu.cleanandroidprojectmvp.inject.modules.InteractorModule;
import com.wangjiegulu.cleanandroidprojectmvp.inject.scope.Scope_User;
import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.interactor.contract.GithubInteractor;
import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.interactor.contract.UserInteractor;

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
