package com.softwareacademy.searchandnavigate.map.dagger;

import com.softwareacademy.searchandnavigate.R;
import com.softwareacademy.searchandnavigate.SearchApplication;
import com.softwareacademy.searchandnavigate.dagger.scopes.ActivityScope;
import com.softwareacademy.searchandnavigate.map.mvp.MapsMVP;
import com.softwareacademy.searchandnavigate.map.mvp.MapsModel;
import com.softwareacademy.searchandnavigate.map.mvp.MapsPresenter;
import com.softwareacademy.searchandnavigate.network.GoogleRetrofitProvider;
import com.softwareacademy.searchandnavigate.network.api.GooglePlacesApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 *
 */
@Module
public class MapsActivityModule {
    MapsMVP.View view;

    public MapsActivityModule(MapsMVP.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    MapsMVP.Model provideModel(GoogleRetrofitProvider googleRetrofitProvider
            , SearchApplication searchApplication) {

        Retrofit retrofit = googleRetrofitProvider.provideRetrofit();
        GooglePlacesApi api = retrofit.create(GooglePlacesApi.class);
        String apiKey = searchApplication.getString(R.string.places_api_key);

        return new MapsModel(api, apiKey);
    }



    @ActivityScope
    @Provides
    MapsMVP.Presenter providePresenter(MapsMVP.Model model){
        return new MapsPresenter(model,view);
    }

}
