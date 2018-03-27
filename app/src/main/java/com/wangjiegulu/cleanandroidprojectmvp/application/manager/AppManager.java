package com.wangjiegulu.cleanandroidprojectmvp.application.manager;

import android.support.annotation.Nullable;
import android.util.Log;

import com.wangjiegulu.cleanandroidprojectmvp.application.CAPApplication;
import com.wangjiegulu.cleanandroidprojectmvp.inject.app.AppModule;
import com.wangjiegulu.cleanandroidprojectmvp.inject.app.DaggerAppComponent;
import com.wangjiegulu.cleanandroidprojectmvp.inject.modules.InteractorModule;
import com.wangjiegulu.cleanandroidprojectmvp.inject.user.DaggerUserComponent;
import com.wangjiegulu.cleanandroidprojectmvp.inject.user.UserModule;
import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.application.ProviderApplication;
import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.interactor.contract.GlobalInteractor;
import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.interactor.contract.UserInteractor;
import com.wangjiegulu.cleanandroidprojectmvp.provider.dal.db.helper.DatabaseFactory;
import com.wangjiegulu.cleanandroidprojectmvp.provider.dal.db.model.User;

import static android.content.ContentValues.TAG;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 23/03/2018.
 */
public class AppManager {

    /**
     * Switch to user login status automatically which last login when launch the app.
     */
    public static void autoSwitchUser() {
        CAPApplication application = CAPApplication.instance;
        application.appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(application))
                .build();
        GlobalInteractor globalInteractor = application.appComponent.providerGlobalInteractor();
        long userId = globalInteractor.queryGlobalCurrentLoginUserIdSync();
        Log.i(TAG, "-----------> autoSwitchUser, userId: " + userId);
        doSwitchUser(userId, null);
    }

    /**
     * Switch user, it will be:
     *
     * 1. Switch userComponent.
     * 2. Save userId as the last login status to global file.
     * 3. Switch user and reset user database(DatabaseFactory).
     * 4. Notify switch user to ProviderApplication.
     * 5. Save user info to user database.
     *
     */
    public static void doSwitchUser(long userId, @Nullable User user) {
        Log.i(TAG, "-----------> switchUser, userId: " + userId + ", User: " + user);

        CAPApplication application = CAPApplication.instance;
        // 如果用户id和传入的userId不一致，则默认使用未登录状态
        if (null != user && user.getUserIdDefaultNotLogin() != userId) {
            user = null;
            userId = User.USER_NOT_LOGIN_USER_ID;
        }

        /* 1 */
        application.userComponent = DaggerUserComponent
                .builder()
                .userModule(new UserModule(application))
                .interactorModule(new InteractorModule())
                .appComponent(application.appComponent)
                .build();

        /* 2 */
        application.appComponent.providerGlobalInteractor().saveGlobalCurrentLoginUserIdSync(userId);

        /* 3 */
        // TODO: 4/17/17 wangjie optim databaseFactory scope
        DatabaseFactory.getInstance().resetDatabase(
                ProviderApplication.getInstance().getCurrentUserCode(userId) + ".db"
        );
        /* 4 */
        ProviderApplication.getInstance().onSwitchUser();

        /* 5 */
        if (null != user) {
            UserInteractor userInteractor = application.userComponent.providerUserInteractor();
            userInteractor.saveLoginInfoSync(user);
        }

    }
}
