package com.softwareacademy.searchandnavigate.login.dagger;

import com.softwareacademy.searchandnavigate.dagger.SearchComponent;

import dagger.Component;

/**
 *
 */
@Component(modules = LoginActivityModule.class, dependencies = SearchComponent.class)
public interface LoginActivityComponent {
}
