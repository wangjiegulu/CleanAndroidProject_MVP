package com.wangjiegulu.cleanandroidprojectmvp.provider.bll.inject.cache;


import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.inject.scope.Provider_Scope_User;
import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.interactor.cache.CurrentLoginCache;

import dagger.Module;
import dagger.Provides;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 11/15/16.
 */
@Module
public class ProviderUserCacheModule {
    @Provides
    @Provider_Scope_User
    public CurrentLoginCache providerCurrentLoginCache() {
        return new CurrentLoginCache();
    }

}
