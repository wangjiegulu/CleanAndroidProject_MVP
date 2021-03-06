package com.wangjiegulu.capmvp.provider.bll.interactor.impl;

import com.wangjiegulu.capmvp.provider.bll.interactor.base.BaseInteractor;
import com.wangjiegulu.capmvp.provider.bll.interactor.contract.GlobalInteractor;
import com.wangjiegulu.capmvp.provider.dal.prefs.PrefsConstants;
import com.wangjiegulu.capmvp.provider.dal.prefs.PrefsRepository;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Author: wangjie Email: tiantian.china.2@gmail.com Date: 10/31/16.
 */
public class GlobalInteractorImpl extends BaseInteractor implements GlobalInteractor {
    @Inject
    @Named(PrefsConstants.PREFS_GLOBAL)
    PrefsRepository globalPrefsRepository;

    public GlobalInteractorImpl() {
        getProviderApplicationComponent().inject(this);
    }

//    @Override
//    public long queryGlobalCurrentLoginUserIdSync() {
//        return globalPrefsRepository.getLong(PrefsConstants.PREFS_GLOBAL_USER_ID, User.USER_NOT_LOGIN_USER_ID);
//    }
//
//    @Override
//    public void saveGlobalCurrentLoginUserIdSync(long userId) {
//        globalPrefsRepository.putLong(PrefsConstants.PREFS_GLOBAL_USER_ID, userId).commit();
//    }

}
