package com.softwareacademy.searchandnavigate.login.dagger;

import com.softwareacademy.searchandnavigate.dagger.SearchComponent;
import com.softwareacademy.searchandnavigate.dagger.scopes.ActivityScope;
import com.softwareacademy.searchandnavigate.login.LoginActivity;

import dagger.Component;

/**
 *
 */
@ActivityScope
@Component(modules = LoginActivityModule.class, dependencies = SearchComponent.class)
public interface LoginActivityComponent {
    void inject(LoginActivity loginActivity);
}
