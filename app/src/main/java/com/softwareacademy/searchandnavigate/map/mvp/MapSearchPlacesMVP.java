package com.softwareacademy.searchandnavigate.map.mvp;

import com.softwareacademy.searchandnavigate.model.dto.PlacesDto;
import com.softwareacademy.searchandnavigate.utils.network.ClearSubscribtion;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

/**
 *
 */

public interface MapSearchPlacesMVP {

    interface Model {
        Observable<List<PlacesDto>> getPlaces(Map<String, String> queryMap);
    }

    interface Presenter extends ClearSubscribtion {
        void askForPlaces(Map<String, String> queryMap);
    }

    interface View {
        void showPlacesOnMap(List<PlacesDto> placesDtos);
    }

}
