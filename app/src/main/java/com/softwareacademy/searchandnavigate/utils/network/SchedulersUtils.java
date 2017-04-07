package com.softwareacademy.searchandnavigate.utils.network;

import rx.Scheduler;

public interface SchedulersUtils {
    Scheduler subscribeScheduler();
    Scheduler observScheduler();
}