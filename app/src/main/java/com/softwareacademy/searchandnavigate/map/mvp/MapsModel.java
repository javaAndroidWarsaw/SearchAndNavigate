package com.softwareacademy.searchandnavigate.map.mvp;

import com.softwareacademy.searchandnavigate.model.dto.PlacesDto;
import com.softwareacademy.searchandnavigate.model.dto.SearchParamsDto;
import com.softwareacademy.searchandnavigate.model.json.Result;
import com.softwareacademy.searchandnavigate.network.api.GooglePlacesApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

/**
 *
 */

public class MapsModel implements MapsMVP.Model {

    public static final String KEY = "key";
    private GooglePlacesApi googlePlacesApi;
    private String apiKey;

    public MapsModel(GooglePlacesApi googlePlacesApi, String apiKey) {
        this.googlePlacesApi = googlePlacesApi;
        this.apiKey = apiKey;
    }

    @Override
    public Observable<List<PlacesDto>> getPlacesDto(SearchParamsDto searchParamsDto) {
        Map<String, String> querys = searchParamsDto.getQuerys();
        querys.put(KEY,apiKey);

        return googlePlacesApi.getGooglePlaces(searchParamsDto.getSearchType()
                , querys)
                .map(googlePlacesResponse -> {
                    List<Result> results = googlePlacesResponse.getResults();
                    List<PlacesDto> placesDtos = new ArrayList<>();
                    for (Result result : results) {
                        placesDtos.add(new PlacesDto(result));
                    }
                    return placesDtos;
                });
    }
}
