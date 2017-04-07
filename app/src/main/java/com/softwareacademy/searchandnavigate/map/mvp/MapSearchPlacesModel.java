package com.softwareacademy.searchandnavigate.map.mvp;

import com.softwareacademy.searchandnavigate.model.dto.PlacesDto;
import com.softwareacademy.searchandnavigate.model.json.Result;
import com.softwareacademy.searchandnavigate.network.api.PlacesApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.Observable;

/**
 *
 */

public class MapSearchPlacesModel implements MapSearchPlacesMVP.Model {

    private static final String KEY = "key";
    private PlacesApi placesApi;
    private String apiKey;

    public MapSearchPlacesModel(PlacesApi placesApi, String apiKey) {
        this.placesApi = placesApi;
        this.apiKey = apiKey;
    }

    @Override
    public Observable<List<PlacesDto>> getPlaces(Map<String, String> queryMap) {
        queryMap.put(KEY,apiKey);
        return placesApi.getListOfPlaces(queryMap).map(it -> {
            List<PlacesDto> placesDtos = new ArrayList<>();
            for (Result result : it.getResults()) {
                placesDtos.add(new PlacesDto(result));
            }
            return placesDtos;
        });
    }
}
