package com.wangjiegulu.capmvp.provider.bll.base;

import com.wangjiegulu.capmvp.provider.bll.application.configuration.scheduler.ProviderSchedulers;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import io.reactivex.Scheduler;
import io.reactivex.internal.schedulers.ExecutorScheduler;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 27/03/2018.
 */
public class ProviderImmediateSchedulerRule implements TestRule {
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
//                RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> immediateScheduler);
//                RxJavaPlugins.setInitComputationSchedulerHandler(schedulerCallable -> immediateScheduler);
//                RxJavaPlugins.setInitIoSchedulerHandler(schedulerCallable -> immediateScheduler);
//                RxJavaPlugins.setInitNewThreadSchedulerHandler(schedulerCallable -> immediateScheduler);
//                RxJavaPlugins.setInitSingleSchedulerHandler(schedulerCallable -> immediateScheduler);

                ProviderSchedulers.setAllSchedulers(immediateScheduler);
                try {
                    base.evaluate();
                } finally {
//                    RxJavaPlugins.reset();
//                    RxAndroidPlugins.reset();
                }
            }
        };
    }
}
