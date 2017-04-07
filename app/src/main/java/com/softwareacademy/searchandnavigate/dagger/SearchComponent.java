package com.softwareacademy.searchandnavigate.dagger;

import com.softwareacademy.searchandnavigate.utils.network.SchedulersUtils;
import com.softwareacademy.searchandnavigate.utils.shared_preferences.SharedPreferencesFacade;

import javax.inject.Singleton;

import dagger.Component;

/**
 *
 */

@Singleton
@Component(modules = {MainAppModule.class, InterfaceModule.class})
public interface SearchComponent {
    /**
     * Pozwala widzieć w całej aplikacji ten obiekt
     */
    SharedPreferencesFacade provideSharedPreferencesFacade();

    SchedulersUtils provideSchedulersUtils();
}
