package com.wangjiegulu.cleanandroidprojectmvp.provider.bll.interactor.cache;

import android.support.annotation.NonNull;
import android.util.Log;

import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.application.ProviderApplication;
import com.wangjiegulu.cleanandroidprojectmvp.provider.dal.db.model.User;
import com.wangjiegulu.cleanandroidprojectmvp.provider.dal.prefs.PrefsConstants;

import static com.wangjiegulu.cleanandroidprojectmvp.provider.dal.db.model.User.USER_NOT_LOGIN;

/**
 * Author: wangjie Email: tiantian.china.2@gmail.com Date: 11/14/16.
 */
public class CurrentLoginCache {
    private static final String TAG = CurrentLoginCache.class.getSimpleName();
    /**
     * 当前登录的用户缓存
     */
    private User user;

    /**
     * 同步查询当前登录的用户
     */
    @NonNull
    @SuppressWarnings("PMD.AvoidDeeplyNestedIfStmts")
    public User getCurrentUser() {
        if (null == user) {
            synchronized (this) {
                if (null == user) {
                    try {
                        ProviderApplication providerApplication = ProviderApplication.getInstance();
                        long userId = providerApplication.providerApplicationComponent.providerGlobalPrefsHelper()
                                .getLong(PrefsConstants.PREFS_GLOBAL_USER_ID, User.USER_NOT_LOGIN_USER_ID);
                        if (User.USER_NOT_LOGIN_USER_ID != userId) {
                            user = providerApplication.providerUserInteractorComponent.providerUserDao().queryUser(userId);
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "getCurrentUser", e);
                    }
                    if (null == user) {
                        user = USER_NOT_LOGIN;
                    }
                }
            }
        }
        return user;
    }

    /**
     * 是否是登录状态
     */
    public boolean isLogin() {
        return USER_NOT_LOGIN != getCurrentUser();
    }

//    /**
//     * 获取用户token
//     *
//     * @return 如果是未登录用户，则返回null
//     */
//    @Nullable
//    public String getUtoken() {
//        return getCurrentUser().getUtoken();
//    }

    /**
     * 清除缓存
     */
    public void clear() {
        user = null;
    }
}
