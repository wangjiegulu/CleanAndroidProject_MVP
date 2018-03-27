package com.wangjiegulu.cleanandroidprojectmvp.provider.bll.inject.net;

import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.inject.scope.Provider_Scope_User;
import com.wangjiegulu.cleanandroidprojectmvp.provider.dal.net.http.XRequestCreator;

import dagger.Module;
import dagger.Provides;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 11/15/16.
 */
@Module
public class ProviderUserNetworkModule {
    @Provides
    @Provider_Scope_User
    public XRequestCreator providerXRequestCreator() {
        return new XRequestCreator();
    }

}
