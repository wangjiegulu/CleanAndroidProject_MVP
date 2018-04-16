package com.wangjiegulu.capmvp.application.configuration.scheduler;

import android.support.annotation.VisibleForTesting;

import com.wangjiegulu.capmvp.usagesupport.scheduler.SchedulerSelector;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 27/03/2018.
 */
public final class AppSchedulers {
    private AppSchedulers() {
    }

    private static final int MAIN = 0x1783;

    public static Scheduler main() {
        return SchedulerSelector.get().getScheduler(MAIN);
    }

    public static void initialize() {
        SchedulerSelector.get().putScheduler(MAIN, AndroidSchedulers::mainThread);
    }

    @VisibleForTesting
    public static void setAllSchedulers(Scheduler scheduler) {
        SchedulerSelector.get().putScheduler(MAIN, () -> scheduler);
    }
}
