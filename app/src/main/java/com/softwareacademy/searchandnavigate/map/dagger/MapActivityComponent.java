package com.softwareacademy.searchandnavigate.map.dagger;

import com.softwareacademy.searchandnavigate.dagger.SearchComponent;
import com.softwareacademy.searchandnavigate.dagger.scopes.ActivityScope;
import com.softwareacademy.searchandnavigate.map.MapActivity;

import dagger.Component;

/**
 *
 */@ActivityScope
@Component(modules = MapActivityModule.class,dependencies = SearchComponent.class)
public interface MapActivityComponent {
    void inject(MapActivity mapActivity);
}
