package com.softwareacademy.searchandnavigate.network.api;


import com.softwareacademy.searchandnavigate.model.json.GooglePlaceResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 *
 */

public interface PlacesApi {

    @GET("maps/api/place/textsearch/json")
    Observable<GooglePlaceResponse> getListOfPlaces(@QueryMap Map<String,String> mapOfQuerys);
}
