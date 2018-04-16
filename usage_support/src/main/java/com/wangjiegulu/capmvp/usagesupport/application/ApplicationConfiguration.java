package com.wangjiegulu.capmvp.usagesupport.application;

import android.app.Application;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 30/03/2018.
 */
public class ApplicationConfiguration {

    private Application application;
    private boolean buildConfigDebug;

    public Application getApplication() {
        return application;
    }

    public ApplicationConfiguration setApplication(Application application) {
        this.application = application;
        return this;
    }

    public boolean isBuildConfigDebug() {
        return buildConfigDebug;
    }

    public ApplicationConfiguration setBuildConfigDebug(boolean buildConfigDebug) {
        this.buildConfigDebug = buildConfigDebug;
        return this;
    }
}
