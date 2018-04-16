package com.wangjiegulu.capmvp.application;

import android.app.Application;
import android.os.Looper;
import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.wangjiegulu.capmvp.BuildConfig;
import com.wangjiegulu.capmvp.application.configuration.scheduler.AppSchedulers;
import com.wangjiegulu.capmvp.provider.bll.application.BllApplication;
import com.wangjiegulu.capmvp.usagesupport.application.ApplicationConfiguration;
import com.wangjiegulu.capmvp.inject.app.AppComponent;
import com.wangjiegulu.capmvp.inject.app.AppModule;
import com.wangjiegulu.capmvp.inject.app.DaggerAppComponent;
import com.wangjiegulu.capmvp.inject.modules.InteractorModule;
import com.wangjiegulu.capmvp.inject.user.DaggerUserComponent;
import com.wangjiegulu.capmvp.inject.user.UserComponent;
import com.wangjiegulu.capmvp.inject.user.UserModule;
import com.wangjiegulu.capmvp.util.AppUtil;
import com.wangjiegulu.capmvp.vo.UserVO;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 23/03/2018.
 */
public class CAPApplication extends Application {
    public static CAPApplication instance;

    public AppComponent appComponent;
    public UserComponent userComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        if (AppUtil.isMainProcess(this)) {
            initMainProcess();
        }

    }

    private void initMainProcess() {
        boolean isDebug = BuildConfig.DEBUG;

        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();

        // init app schedulers
        AppSchedulers.initialize();

        // Provider
        BllApplication.getInstance()
                .setApplicationConfiguration(
                        new ApplicationConfiguration()
                                .setApplication(this)
                                .setBuildConfigDebug(isDebug)
                )
                .initialize();

        // RxBus
//        RxBus2.setDebug(isDebug);

        // init user login status
        doSwitchUser();
    }

    /**
     * Switch to user login status automatically which last login when launch the app.
     */
    public void doSwitchUser() {
        // switch user login status of model layer.
        BllApplication.getInstance().doSwitchUser();
        // switch user login status of view layer
        createUserComponent();
    }

    /**
     * Switch to special user login status
     */
    public void doSwitchUser(@NonNull UserVO user) {
        // switch user login status of model layer.
        BllApplication.getInstance().doSwitchUser(user.toUserBO());
        // switch user login status of view layer
        createUserComponent();
    }

    private void createUserComponent() {
        /* 1 */
        userComponent = DaggerUserComponent
                .builder()
                .userModule(new UserModule(this))
                .interactorModule(new InteractorModule())
                .appComponent(appComponent)
                .build();
    }


    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (AppUtil.isMainProcess(this)) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                Glide.get(this).trimMemory(level);
            }
        }

    }

}
