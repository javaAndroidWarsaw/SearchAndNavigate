package com.softwareacademy.searchandnavigate.network.providers;

import com.softwareacademy.searchandnavigate.network.ApiProviderAbstract;
import com.softwareacademy.searchandnavigate.network.api.PlacesApi;

/**
 *
 */

public class GooglePlacesApiProvider extends ApiProviderAbstract<PlacesApi> {
    public GooglePlacesApiProvider() {
        super("https://maps.googleapis.com/");
    }

    @Override
    protected Class<PlacesApi> getApiClass() {
        return PlacesApi.class;
    }
}
