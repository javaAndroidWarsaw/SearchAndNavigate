package com.softwareacademy.searchandnavigate.utils.network;

import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 *
 */
@Singleton
public class SchedulersUtilsImpl implements SchedulersUtils {
    private Scheduler subsribers, observers;

    @Inject
    public SchedulersUtilsImpl() {
        this.observers = AndroidSchedulers.mainThread();
        this.subsribers = Schedulers.from(Executors.newFixedThreadPool(4));
    }

    @Override
    public Scheduler subscribeScheduler() {
        return subsribers;
    }

    @Override
    public Scheduler observScheduler() {
        return observers;
    }
}
