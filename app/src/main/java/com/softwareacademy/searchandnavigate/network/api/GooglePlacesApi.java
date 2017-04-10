package com.softwareacademy.searchandnavigate.network.api;

import com.softwareacademy.searchandnavigate.model.json.GooglePlacesResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 *
 */

public interface GooglePlacesApi {


    @GET("api/place/{searchType}/json")
    Observable<GooglePlacesResponse> getGooglePlaces(@Path("searchType") String searchType,
                                                     @QueryMap Map<String, String> mapOfParams);

}
