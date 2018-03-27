package com.wangjiegulu.cleanandroidprojectmvp.application;

import android.app.Application;
import android.os.Looper;

import com.bumptech.glide.Glide;
import com.wangjie.rapidorm.constants.RapidORMConfig;
import com.wangjiegulu.cleanandroidprojectmvp.BuildConfig;
import com.wangjiegulu.cleanandroidprojectmvp.application.configuration.network.interceptor.GithubRequestInterceptor;
import com.wangjiegulu.cleanandroidprojectmvp.application.configuration.scheduler.AppSchedulers;
import com.wangjiegulu.cleanandroidprojectmvp.application.manager.AppManager;
import com.wangjiegulu.cleanandroidprojectmvp.inject.app.AppComponent;
import com.wangjiegulu.cleanandroidprojectmvp.inject.app.AppModule;
import com.wangjiegulu.cleanandroidprojectmvp.inject.app.DaggerAppComponent;
import com.wangjiegulu.cleanandroidprojectmvp.inject.user.UserComponent;
import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.application.ProviderApplication;
import com.wangjiegulu.cleanandroidprojectmvp.util.AppUtil;
import com.wangjiegulu.dal.request.XHttpManager;
import com.wangjiegulu.dal.request.gson.DefaultGsonResponseConverter;

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

        // Provider
        ProviderApplication.getInstance()
                .setApplication(this)
                .setBuildConfigDebug(isDebug);

        // init app schedulers
        AppSchedulers.initialize();

        // configuration dal_request
        XHttpManager.getInstance()
                .addRequestInterceptor(new GithubRequestInterceptor())
                .setResponseConverter(DefaultGsonResponseConverter.create())
                .setDebug(isDebug);

        // rapidorm
        RapidORMConfig.DEBUG = isDebug;

        // RxBus
//        RxBus2.setDebug(isDebug);

        // 自动切换到上次登录的用户
        AppManager.autoSwitchUser();

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
