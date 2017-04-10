package com.softwareacademy.searchandnavigate.map.dagger;

import com.softwareacademy.searchandnavigate.dagger.SearchComponent;
import com.softwareacademy.searchandnavigate.dagger.scopes.ActivityScope;
import com.softwareacademy.searchandnavigate.map.MapsActivity;

import dagger.Component;

/**
 *
 */
@ActivityScope
@Component(dependencies = SearchComponent.class)
public interface MapsActivityComponent {
    void inject(MapsActivity mapsActivity);

}
