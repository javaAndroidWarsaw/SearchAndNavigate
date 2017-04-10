package com.softwareacademy.searchandnavigate.dagger;

import com.softwareacademy.searchandnavigate.SearchApplication;
import com.softwareacademy.searchandnavigate.network.GoogleRetrofitProvider;

import javax.inject.Singleton;

import dagger.Component;

/**
 *
 */

@Singleton
@Component(modules = {MainAppModule.class, InterfaceModule.class})
public interface SearchComponent {
    GoogleRetrofitProvider provideGoogleRetrofitProvider();

    SearchApplication provideSearchApplication();
}
