package com.wangjiegulu.cleanandroidprojectmvp.provider.bll.interactor.impl;

import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.application.configuration.scheduler.ProviderSchedulers;
import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.interactor.base.BaseInteractor;
import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.interactor.cache.CurrentLoginCache;
import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.interactor.contract.UserInteractor;
import com.wangjiegulu.cleanandroidprojectmvp.provider.dal.db.dao.contract.UserDao;
import com.wangjiegulu.cleanandroidprojectmvp.provider.dal.db.model.User;
import com.wangjiegulu.cleanandroidprojectmvp.provider.dal.prefs.PrefsConstants;
import com.wangjiegulu.cleanandroidprojectmvp.provider.dal.prefs.PrefsHelper;
import com.wangjiegulu.cleanandroidprojectmvp.provider.dal.net.http.XRequestCreator;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;

/**
 * Author: wangjie Email: tiantian.china.2@gmail.com Date: 10/31/16.
 */
public class UserInteractorImpl extends BaseInteractor implements UserInteractor {

    @Inject
    @Named(PrefsConstants.PREFS_GLOBAL)
    PrefsHelper globalPrefsHelper;

    @Inject
    @Named(PrefsConstants.PREFS_USER)
    PrefsHelper userPrefsHelper;

    @Inject
    UserDao userDao;

    @Inject
    CurrentLoginCache currentLoginCache;

    @Inject
    XRequestCreator xRequestCreator;

    public UserInteractorImpl() {
        getProviderUserInteractorComponent().inject(this);
    }

    @Override
    public Observable<User> getCurrentUserInfo() {
        return toObservable(() -> currentLoginCache.getCurrentUser()).subscribeOn(ProviderSchedulers.db());
    }

    @Override
    public void saveLoginInfoSync(User user) {
        // TODO: 02/08/2017 wangjie
    }

}
