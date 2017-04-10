package com.softwareacademy.searchandnavigate.utils.network;


import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 *
 */

public class AbstractClearSubscribtion implements ClearSubscribtion {

    private CompositeDisposable compositeSubscription = new CompositeDisposable();


    protected void addToSubsctibiton(Disposable subscription) {
        compositeSubscription.add(subscription);
    }

    @Override
    public void clearSubscribtions() {
        compositeSubscription.clear();
    }
}
