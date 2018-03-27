package com.wangjiegulu.cleanandroidprojectmvp.base;

import com.wangjiegulu.cleanandroidprojectmvp.application.configuration.scheduler.AppSchedulers;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 27/03/2018.
 */
public class AppImmediateSchedulerRule implements TestRule {
    private Scheduler immediateScheduler = new Scheduler() {
        @Override
        public Worker createWorker() {
            return new ExecutorScheduler.ExecutorWorker(Runnable::run);
        }
    };

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> immediateScheduler);
                RxJavaPlugins.setInitComputationSchedulerHandler(schedulerCallable -> immediateScheduler);
                RxJavaPlugins.setInitIoSchedulerHandler(schedulerCallable -> immediateScheduler);
                RxJavaPlugins.setInitNewThreadSchedulerHandler(schedulerCallable -> immediateScheduler);
                RxJavaPlugins.setInitSingleSchedulerHandler(schedulerCallable -> immediateScheduler);

                AppSchedulers.setAllSchedulers(immediateScheduler);
                try {
                    base.evaluate();
                } finally {
                    RxJavaPlugins.reset();
                    RxAndroidPlugins.reset();
                }
            }
        };
    }
}
