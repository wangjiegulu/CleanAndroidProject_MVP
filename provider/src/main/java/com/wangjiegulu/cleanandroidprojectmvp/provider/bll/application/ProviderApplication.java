package com.wangjiegulu.cleanandroidprojectmvp.provider.bll.application;

import android.annotation.SuppressLint;
import android.app.Application;

import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.application.configuration.scheduler.ProviderSchedulers;
import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.inject.application.DaggerProviderApplicationComponent;
import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.inject.application.ProviderApplicationComponent;
import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.inject.db.ProviderUserDatabaseModule;
import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.inject.interactor.DaggerProviderUserInteractorComponent;
import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.inject.interactor.ProviderUserInteractorComponent;
import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.inject.prefs.ProviderApplicationPrefsModule;
import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.inject.prefs.ProviderUserPrefsModule;
import com.wangjiegulu.cleanandroidprojectmvp.provider.dal.db.model.User;
import com.wangjiegulu.cleanandroidprojectmvp.provider.dal.prefs.PrefsConstants;

/**
 * 与主项目之间上下文环境的同步
 * <p>
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 10/27/16.
 */
public class ProviderApplication {
    private static class Holder {
        @SuppressLint("StaticFieldLeak")
        private static ProviderApplication instance = new ProviderApplication();
    }

    private Application application;
    private boolean buildConfigDebug;

    public ProviderApplicationComponent providerApplicationComponent;
    public ProviderUserInteractorComponent providerUserInteractorComponent;

    public static ProviderApplication getInstance() {
        return Holder.instance;
    }

    private static Boolean isProdEnvCache;

    //    @Discard(apply = DiscardApplyConstants.Publish._TRUE, srcCode = "{ return true; }")
    public static boolean isProdEnv() {
        if (null == isProdEnvCache) {
            synchronized (ProviderApplication.class) {
                if (null == isProdEnvCache) {
                    isProdEnvCache = ProviderApplication.getInstance().providerApplicationComponent
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

    private ProviderApplication() {
    }

    public ProviderApplication setApplication(Application application) {
        this.application = application;

        providerApplicationComponent = DaggerProviderApplicationComponent.builder()
                .providerApplicationPrefsModule(new ProviderApplicationPrefsModule())
                .build();

        // init provider schedulers
        ProviderSchedulers.initialize();
        return this;
    }

    /**
     * 每次切换用户都需要调用此方法来使用新的UserInteractor
     */
    public void onSwitchUser() {
        providerUserInteractorComponent = DaggerProviderUserInteractorComponent.builder()
                .providerApplicationComponent(providerApplicationComponent)
                .providerUserDatabaseModule(new ProviderUserDatabaseModule())
                .providerUserPrefsModule(new ProviderUserPrefsModule())
                .build();
    }

    public Application getApplication() {
        return application;
    }

    public ProviderApplication setBuildConfigDebug(boolean buildConfigDebug) {
        this.buildConfigDebug = buildConfigDebug;
        return this;
    }

    public boolean isBuildConfigDebug() {
        return buildConfigDebug;
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

    /**
     * 获取用户数据库文件
     * <p>
     * 如果是生产环境，则为"用户的id"；
     * 如果是测试环境，则为"用户id+debug"
     */
    public String getCurrentUserDataBase() {
        return getCurrentUserCode() + ".db";
    }
}
