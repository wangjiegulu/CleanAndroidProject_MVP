package com.wangjiegulu.capmvp.provider.bll.application;

import android.annotation.SuppressLint;
import android.app.Application;
import android.support.annotation.NonNull;
import android.util.Log;

import com.wangjiegulu.capmvp.provider.bll.application.configuration.scheduler.ProviderSchedulers;
import com.wangjiegulu.capmvp.provider.bll.inject.application.DaggerProviderApplicationComponent;
import com.wangjiegulu.capmvp.provider.bll.inject.application.ProviderApplicationComponent;
import com.wangjiegulu.capmvp.provider.bll.inject.db.ProviderUserDatabaseModule;
import com.wangjiegulu.capmvp.provider.bll.inject.interactor.DaggerProviderUserInteractorComponent;
import com.wangjiegulu.capmvp.provider.bll.inject.interactor.ProviderUserInteractorComponent;
import com.wangjiegulu.capmvp.provider.bll.inject.prefs.ProviderApplicationPrefsModule;
import com.wangjiegulu.capmvp.provider.bll.inject.prefs.ProviderUserPrefsModule;
import com.wangjiegulu.capmvp.provider.bll.interactor.bo.UserBO;
import com.wangjiegulu.capmvp.provider.dal.application.DalApplication;
import com.wangjiegulu.capmvp.provider.dal.db.po.User;
import com.wangjiegulu.capmvp.provider.dal.prefs.PrefsConstants;
import com.wangjiegulu.capmvp.provider.dal.proxy.db.factory.DatabaseFactoryProxy;
import com.wangjiegulu.capmvp.usagesupport.application.ApplicationConfiguration;

/**
 * 与主项目之间上下文环境的同步
 * <p>
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 10/27/16.
 */
public class BllApplication {
    private static final String TAG = BllApplication.class.getSimpleName();

    private static class Holder {
        @SuppressLint("StaticFieldLeak")
        private static BllApplication instance = new BllApplication();
    }

    private ApplicationConfiguration applicationConfiguration;

    public ProviderApplicationComponent providerApplicationComponent;
    public ProviderUserInteractorComponent providerUserInteractorComponent;

    public static BllApplication getInstance() {
        return Holder.instance;
    }

    private static Boolean isProdEnvCache;

    //    @Discard(apply = DiscardApplyConstants.Publish._TRUE, srcCode = "{ return true; }")
    public static boolean isProdEnv() {
        if (null == isProdEnvCache) {
            synchronized (BllApplication.class) {
                if (null == isProdEnvCache) {
                    isProdEnvCache = BllApplication.getInstance().providerApplicationComponent
                            .providerGlobalPrefsHelper()
                            .getBoolean(PrefsConstants.PREFS_GLOBAL_IS_PROD_ENV, false);
                }
            }
        }
        return isProdEnvCache;
    }

    /**
     * 每次生产测试环境切换时需要清空cache
     */
    public static void clearProdEnvCache() {
        isProdEnvCache = null;
    }

    private BllApplication() {
    }

    public BllApplication setApplicationConfiguration(ApplicationConfiguration applicationConfiguration) {
        this.applicationConfiguration = applicationConfiguration;
        DalApplication.getInstance().setApplicationConfiguration(applicationConfiguration);
        return this;
    }

    public void initialize() {
        providerApplicationComponent = DaggerProviderApplicationComponent.builder()
                .providerApplicationPrefsModule(new ProviderApplicationPrefsModule())
                .build();

//        boolean isDebug = applicationConfiguration.isBuildConfigDebug();

        // init provider schedulers
        ProviderSchedulers.initialize();

        DalApplication.getInstance().initialize();
    }

    /**
     * Switch to user login status automatically which last login when launch the app.
     */
    public void doSwitchUser() {
        long userId = providerApplicationComponent.providerGlobalPrefsHelper()
                .getLong(PrefsConstants.PREFS_GLOBAL_USER_ID, User.USER_NOT_LOGIN_USER_ID);
        Log.i(TAG, "-----------> Auto switch user, userId: " + userId);
        doSwitchUserInternal(userId);
    }

    /**
     * Switch to special user login status
     */
    public void doSwitchUser(@NonNull UserBO user) {
        if (null == user.getUserId()) {
            user = UserBO.create(User.USER_NOT_LOGIN);
        }
        Log.i(TAG, "-----------> switchUser, userId: " + user.getUserId() + ", User: " + user);
        doSwitchUserInternal(user.getUserId());

        /* 5 */
        try {
            providerUserInteractorComponent.providerUserDao().insertOrUpdate(user.toUser());
        } catch (Exception e) {
            Log.e(TAG, "doSwitchUser insertOrUpdate user", e);
        }
    }

    /**
     * Switch user, it will be:
     *
     * 1. Switch userComponent.
     * 2. Save userId as the last login status to global file.
     * 3. Switch user and reset user database(DatabaseFactory).
     * 4. Notify switch user to BllApplication.
     * 5. Save user info to user database.
     */
    private void doSwitchUserInternal(long userId) {
        Log.i(TAG, "-----------> doSwitchUserInternal, userId: " + userId);
        /* 2 */
//        application.appComponent.providerGlobalInteractor().saveGlobalCurrentLoginUserIdSync(userId);
        providerApplicationComponent.providerGlobalPrefsHelper()
                .putLong(PrefsConstants.PREFS_GLOBAL_USER_ID, userId).commit();
        /* 3 */
        // TODO: 4/17/17 wangjie optim databaseFactory scope
        DatabaseFactoryProxy.getInstance().resetDatabase(
                BllApplication.getInstance().getCurrentUserCode(userId) + ".db"
        );
        /* 4 */
        providerUserInteractorComponent = DaggerProviderUserInteractorComponent.builder()
                .providerApplicationComponent(providerApplicationComponent)
                .providerUserDatabaseModule(new ProviderUserDatabaseModule())
                .providerUserPrefsModule(new ProviderUserPrefsModule())
                .build();
    }

    public Application getApplication() {
        return applicationConfiguration.getApplication();
    }

    /**
     * 当前登录用户
     */
    public User getCurrentUser() {
        return providerUserInteractorComponent.providerCurrentLoginCache().getCurrentUser();
    }

    /**
     * 是否登录状态
     */
    public boolean isLogin() {
        return providerUserInteractorComponent.providerCurrentLoginCache().isLogin();
    }

    public String getCurrentUserCode() {
        return getCurrentUserCode(getCurrentUser().getUserId(User.USER_NOT_LOGIN_USER_ID));
    }

    /**
     * 获取用户code
     * <p>
     * 如果是生产环境，则为"用户的id"；
     * 如果是测试环境，则为"用户id+debug"
     */
    public String getCurrentUserCode(long userId) {
        return userId + (isProdEnv() ? "" : "debug");
    }

//    /**
//     * 获取用户数据库文件
//     * <p>
//     * 如果是生产环境，则为"用户的id"；
//     * 如果是测试环境，则为"用户id+debug"
//     */
//    public String getCurrentUserDataBase() {
//        return getCurrentUserCode() + ".db";
//    }
}
