package com.wangjiegulu.capmvp.provider.bll.interactor.impl;

import com.wangjiegulu.capmvp.provider.bll.application.configuration.scheduler.ProviderSchedulers;
import com.wangjiegulu.capmvp.provider.bll.interactor.base.BaseInteractor;
import com.wangjiegulu.capmvp.provider.bll.interactor.cache.CurrentLoginCache;
import com.wangjiegulu.capmvp.provider.bll.interactor.contract.UserInteractor;
import com.wangjiegulu.capmvp.provider.dal.db.dao.contract.UserDao;
import com.wangjiegulu.capmvp.provider.dal.db.po.User;
import com.wangjiegulu.capmvp.provider.dal.http.XRequestRepository;
import com.wangjiegulu.capmvp.provider.dal.prefs.PrefsConstants;
import com.wangjiegulu.capmvp.provider.dal.prefs.PrefsRepository;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;

/**
 * Author: wangjie Email: tiantian.china.2@gmail.com Date: 10/31/16.
 */
public class UserInteractorImpl extends BaseInteractor implements UserInteractor {

    @Inject
    @Named(PrefsConstants.PREFS_GLOBAL)
    PrefsRepository globalPrefsRepository;

    @Inject
    @Named(PrefsConstants.PREFS_USER)
    PrefsRepository userPrefsRepository;

    @Inject
    UserDao userDao;

    @Inject
    CurrentLoginCache currentLoginCache;

    @Inject
    XRequestRepository xRequestRepository;

    public UserInteractorImpl() {
        getProviderUserInteractorComponent().inject(this);
    }

    @Override
    public Observable<User> getCurrentUserInfo() {
        return toObservable(() -> currentLoginCache.getCurrentUser()).subscribeOn(ProviderSchedulers.db());
    }


}
