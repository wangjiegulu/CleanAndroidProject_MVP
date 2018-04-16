package com.wangjiegulu.capmvp.provider.dal.application;

import android.app.Application;

import com.wangjie.rapidorm.constants.RapidORMConfig;
import com.wangjiegulu.capmvp.usagesupport.application.ApplicationConfiguration;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 16/04/2018.
 */
public class DalApplication {
    private static DalApplication dalApplication = new DalApplication();

    private ApplicationConfiguration applicationConfiguration;

    public static DalApplication getInstance() {
        return dalApplication;
    }

    public Application getApplication() {
        return applicationConfiguration.getApplication();
    }

    public DalApplication setApplicationConfiguration(ApplicationConfiguration applicationConfiguration) {
        this.applicationConfiguration = applicationConfiguration;
        return this;
    }

    public void initialize() {
        boolean isDebug = applicationConfiguration.isBuildConfigDebug();

        // rapidorm
        RapidORMConfig.DEBUG = isDebug;
    }


    public static boolean isProdEnv() {
        // TODO: 16/04/2018 wangjie
        return false;
    }
}
