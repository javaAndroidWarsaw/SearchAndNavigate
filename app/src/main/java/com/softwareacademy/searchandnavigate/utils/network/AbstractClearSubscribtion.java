package com.softwareacademy.searchandnavigate.utils.network;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 *
 */

public class AbstractClearSubscribtion implements ClearSubscribtion {

    private CompositeSubscription compositeSubscription = new CompositeSubscription();


    protected void addToSubsctibiton(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    @Override
    public void clearSubscribtions() {
        compositeSubscription.clear();
    }
}
