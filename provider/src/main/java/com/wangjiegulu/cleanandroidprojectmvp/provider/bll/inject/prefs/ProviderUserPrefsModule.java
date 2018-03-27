package com.wangjiegulu.cleanandroidprojectmvp.provider.bll.inject.prefs;

import android.content.Context;

import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.application.ProviderApplication;
import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.inject.scope.Provider_Scope_User;
import com.wangjiegulu.cleanandroidprojectmvp.provider.dal.db.model.User;
import com.wangjiegulu.cleanandroidprojectmvp.provider.dal.prefs.PrefsConstants;
import com.wangjiegulu.cleanandroidprojectmvp.provider.dal.prefs.PrefsHelper;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Author: wangjie Email: tiantian.china.2@gmail.com Date: 11/14/16.
 */
@Module
public class ProviderUserPrefsModule {

    @Provides
    @Named(PrefsConstants.PREFS_USER)
    @Provider_Scope_User
    public PrefsHelper providerUserPrefsHelper() {
        long currentUserId = ProviderApplication.getInstance()
                .providerApplicationComponent
                .providerGlobalPrefsHelper()
                .getLong(PrefsConstants.PREFS_GLOBAL_USER_ID, User.USER_NOT_LOGIN_USER_ID);
        return new PrefsHelper("user_prefs_" + ProviderApplication.getInstance().getCurrentUserCode(currentUserId), Context.MODE_PRIVATE);
    }

}
