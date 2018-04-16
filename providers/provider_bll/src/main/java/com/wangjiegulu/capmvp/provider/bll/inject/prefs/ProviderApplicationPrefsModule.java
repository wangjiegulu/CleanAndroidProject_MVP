package com.wangjiegulu.capmvp.provider.bll.inject.prefs;

import android.content.Context;

import com.wangjiegulu.capmvp.provider.bll.inject.scope.Provider_Scope_Application;
import com.wangjiegulu.capmvp.provider.dal.prefs.PrefsConstants;
import com.wangjiegulu.capmvp.provider.dal.prefs.PrefsHelper;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 11/14/16.
 */
@Module
public class ProviderApplicationPrefsModule {

    @Provides
    @Named(PrefsConstants.PREFS_GLOBAL)
    @Provider_Scope_Application
    public PrefsHelper providerGlobalPrefsHelper() {
        return new PrefsHelper("global_prefs", Context.MODE_PRIVATE);
    }

}
