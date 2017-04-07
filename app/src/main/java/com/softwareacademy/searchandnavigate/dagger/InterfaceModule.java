package com.softwareacademy.searchandnavigate.dagger;

import com.softwareacademy.searchandnavigate.utils.network.SchedulersUtils;
import com.softwareacademy.searchandnavigate.utils.network.SchedulersUtilsImpl;
import com.softwareacademy.searchandnavigate.utils.shared_preferences.SharedPreferencesFacade;
import com.softwareacademy.searchandnavigate.utils.shared_preferences.SharedPreferencesFacadeImpl;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/**
 *
 */
@Module
public abstract class InterfaceModule {

    @Binds
    @Singleton
    public abstract SharedPreferencesFacade provideSharedPreferencesFacade(SharedPreferencesFacadeImpl sharedPreferencesFacade);

    @Binds
    @Singleton
    public abstract SchedulersUtils provideSchedulersUtils(SchedulersUtilsImpl schedulersUtils);
}
