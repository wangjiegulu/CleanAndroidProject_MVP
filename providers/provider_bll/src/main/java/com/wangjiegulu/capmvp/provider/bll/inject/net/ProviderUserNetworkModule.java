package com.wangjiegulu.capmvp.provider.bll.inject.net;

import com.wangjiegulu.capmvp.provider.bll.inject.scope.Provider_Scope_User;
import com.wangjiegulu.capmvp.provider.dal.http.XRequestRepository;

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
    public XRequestRepository providerXRequestCreator() {
        return new XRequestRepository();
    }

}
