package com.softwareacademy.searchandnavigate.network.api;

import com.softwareacademy.searchandnavigate.model.json.GooglePlaceResponse;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 *
 */

public interface PlacesApi {

    @GET("maps/api/place/textsearch/json")
    Observable<GooglePlaceResponse> getListOfPlaces(@QueryMap Map<String,String> mapOfQuerys);
}
