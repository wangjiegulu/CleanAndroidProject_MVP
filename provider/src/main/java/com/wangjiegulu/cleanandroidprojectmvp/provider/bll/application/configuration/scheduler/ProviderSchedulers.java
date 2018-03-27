package com.wangjiegulu.cleanandroidprojectmvp.provider.bll.application.configuration.scheduler;

import android.support.annotation.VisibleForTesting;

import com.wangjiegulu.cleanandroidprojectmvp.provider.support.bridge.testable.rx.scheduler.SchedulerSelector;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

import static com.wangjiegulu.cleanandroidprojectmvp.provider.bll.application.configuration.scheduler.SchedulerBridge.THREAD_POOL_EXECUTOR_DATABASE;
import static com.wangjiegulu.cleanandroidprojectmvp.provider.bll.application.configuration.scheduler.SchedulerBridge.THREAD_POOL_EXECUTOR_NETWORK;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 4/15/16.
 */
public final class ProviderSchedulers {
    private ProviderSchedulers() {
    }

    private static final int NET = 0x8739;
    private static final int DB = 0x1385;

    public static Scheduler net() {
        return SchedulerSelector.get().getScheduler(NET);
    }

    public static Scheduler db() {
        return SchedulerSelector.get().getScheduler(DB);
    }

    public static void initialize() {
        SchedulerSelector schedulerSelector = SchedulerSelector.get();
        schedulerSelector.putScheduler(ProviderSchedulers.NET, () -> Schedulers.from(THREAD_POOL_EXECUTOR_NETWORK));
        schedulerSelector.putScheduler(ProviderSchedulers.DB, () -> Schedulers.from(THREAD_POOL_EXECUTOR_DATABASE));
    }

    @VisibleForTesting
    public static void setAllSchedulers(Scheduler scheduler) {
        SchedulerSelector schedulerSelector = SchedulerSelector.get();
        schedulerSelector.putScheduler(ProviderSchedulers.NET, () -> scheduler);
        schedulerSelector.putScheduler(ProviderSchedulers.DB, () -> scheduler);
    }

}
