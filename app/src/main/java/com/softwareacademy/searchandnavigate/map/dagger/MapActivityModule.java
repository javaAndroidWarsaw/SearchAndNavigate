package com.softwareacademy.searchandnavigate.map.dagger;

import com.softwareacademy.searchandnavigate.R;
import com.softwareacademy.searchandnavigate.SearchApplication;
import com.softwareacademy.searchandnavigate.dagger.scopes.ActivityScope;
import com.softwareacademy.searchandnavigate.map.mvp.MapSearchPlacesMVP;
import com.softwareacademy.searchandnavigate.map.mvp.MapSearchPlacesModel;
import com.softwareacademy.searchandnavigate.map.mvp.MapSearchPresenter;
import com.softwareacademy.searchandnavigate.network.providers.GooglePlacesApiProvider;
import com.softwareacademy.searchandnavigate.utils.network.SchedulersUtils;

import dagger.Module;
import dagger.Provides;

/**
 *
 */


@Module
public class MapActivityModule {


    private MapSearchPlacesMVP.View view;

    public MapActivityModule(MapSearchPlacesMVP.View view) {
        this.view = view;
    }

    @Provides
    @ActivityScope
    MapSearchPlacesMVP.Model provideModel(SearchApplication context) {
        return new MapSearchPlacesModel(new GooglePlacesApiProvider().provideApi(), context.getString(R.string.web_places_key));
    }

    @Provides
    @ActivityScope
    MapSearchPlacesMVP.Presenter providePresenter(SchedulersUtils schedulersUtils, MapSearchPlacesMVP.Model model) {
        return new MapSearchPresenter(model, view, schedulersUtils);
    }


}
