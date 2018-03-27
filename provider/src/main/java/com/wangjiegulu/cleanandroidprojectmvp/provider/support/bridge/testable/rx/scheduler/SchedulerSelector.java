package com.wangjiegulu.cleanandroidprojectmvp.provider.support.bridge.testable.rx.scheduler;

import java.util.HashMap;

import io.reactivex.Scheduler;


/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 4/15/16.
 */
public class SchedulerSelector {

    private HashMap<Integer, SchedulerCreation<Scheduler>> schedulerCreationMapper = new HashMap<>();

    public interface SchedulerCreation<T> {
        T create();
    }

    private static class Holder {
        private static SchedulerSelector instance = new SchedulerSelector();
    }

    private SchedulerSelector() {
    }

    public static SchedulerSelector get() {
        return Holder.instance;
    }

    public SchedulerSelector putScheduler(int schedulerType, SchedulerCreation<Scheduler> schedulerCreation) {
        if (null == schedulerCreation) {
            throw new IllegalArgumentException("SchedulerCreation can not be null.");
        }
        schedulerCreationMapper.put(schedulerType, schedulerCreation);
        return this;
    }

    public Scheduler getScheduler(int scheduleType) {
        SchedulerCreation<Scheduler> schedulerCreation = schedulerCreationMapper.get(scheduleType);
        if (null == schedulerCreation) {
            synchronized (this) {
                schedulerCreation = schedulerCreationMapper.get(scheduleType);
                if (null == schedulerCreation) {
                    throw new IllegalArgumentException("SchedulerCreation of ScheduleType [" + scheduleType + "] is not exist.");
                }
            }
        }
        return schedulerCreation.create();
    }


}
