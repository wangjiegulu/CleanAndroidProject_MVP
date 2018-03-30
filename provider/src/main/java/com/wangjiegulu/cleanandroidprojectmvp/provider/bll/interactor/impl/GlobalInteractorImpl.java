package com.wangjiegulu.cleanandroidprojectmvp.provider.bll.interactor.impl;

import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.interactor.base.BaseInteractor;
import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.interactor.contract.GlobalInteractor;
import com.wangjiegulu.cleanandroidprojectmvp.provider.dal.prefs.PrefsConstants;
import com.wangjiegulu.cleanandroidprojectmvp.provider.dal.prefs.PrefsHelper;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Author: wangjie Email: tiantian.china.2@gmail.com Date: 10/31/16.
 */
public class GlobalInteractorImpl extends BaseInteractor implements GlobalInteractor {
    @Inject
    @Named(PrefsConstants.PREFS_GLOBAL)
    PrefsHelper globalPrefsHelper;

    public GlobalInteractorImpl() {
        getProviderApplicationComponent().inject(this);
    }

//    @Override
//    public long queryGlobalCurrentLoginUserIdSync() {
//        return globalPrefsHelper.getLong(PrefsConstants.PREFS_GLOBAL_USER_ID, User.USER_NOT_LOGIN_USER_ID);
//    }
//
//    @Override
//    public void saveGlobalCurrentLoginUserIdSync(long userId) {
//        globalPrefsHelper.putLong(PrefsConstants.PREFS_GLOBAL_USER_ID, userId).commit();
//    }

}
