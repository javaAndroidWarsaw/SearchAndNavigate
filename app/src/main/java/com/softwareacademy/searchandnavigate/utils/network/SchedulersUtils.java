package com.softwareacademy.searchandnavigate.utils.network;

import io.reactivex.Scheduler;

public interface SchedulersUtils {
    Scheduler subscribeScheduler();
    Scheduler observScheduler();
}