package com.wangjiegulu.capmvp.inject.modules;


import com.wangjiegulu.capmvp.provider.bll.interactor.contract.GithubInteractor;
import com.wangjiegulu.capmvp.provider.bll.interactor.contract.UserInteractor;
import com.wangjiegulu.capmvp.provider.bll.interactor.impl.GithubInteractorImpl;
import com.wangjiegulu.capmvp.provider.bll.interactor.impl.UserInteractorImpl;
import com.wangjiegulu.capmvp.inject.scope.Scope_User;

import dagger.Module;
import dagger.Provides;

/**
 * Author: wangjie Email: tiantian.china.2@gmail.com Date: 10/31/16.
 */
@Module
public class InteractorModule {
    @Provides
    @Scope_User
    public UserInteractor providerUserInteractor() {
        return new UserInteractorImpl();
    }

    @Provides
    @Scope_User
    public GithubInteractor providerGithubInteractor() {
        return new GithubInteractorImpl();
    }
}
