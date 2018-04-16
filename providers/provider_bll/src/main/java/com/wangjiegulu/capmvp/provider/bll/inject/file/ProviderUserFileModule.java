package com.wangjiegulu.capmvp.provider.bll.inject.file;


import com.wangjiegulu.capmvp.provider.bll.application.BllApplication;
import com.wangjiegulu.capmvp.provider.bll.inject.scope.Provider_Scope_User;
import com.wangjiegulu.capmvp.provider.bll.util.MD5Util;
import com.wangjiegulu.capmvp.provider.dal.db.po.User;
import com.wangjiegulu.capmvp.provider.dal.file.FileAccessor;
import com.wangjiegulu.capmvp.provider.dal.file.FileAccessorImpl;
import com.wangjiegulu.capmvp.provider.dal.prefs.PrefsConstants;

import dagger.Module;
import dagger.Provides;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 4/10/17.
 */
@Module
@Provider_Scope_User
public class ProviderUserFileModule {
    @Provides
    @Provider_Scope_User
    FileAccessor providerFileAccessor() {
        // ProviderUserPrefsModule::providerUserPrefsHelper()
        long currentUserId = BllApplication.getInstance()
                .providerApplicationComponent
                .providerGlobalPrefsHelper()
                .getLong(PrefsConstants.PREFS_GLOBAL_USER_ID, User.USER_NOT_LOGIN_USER_ID);
        return new FileAccessorImpl(MD5Util.md5(BllApplication.getInstance().getCurrentUserCode(currentUserId)));
    }
}
