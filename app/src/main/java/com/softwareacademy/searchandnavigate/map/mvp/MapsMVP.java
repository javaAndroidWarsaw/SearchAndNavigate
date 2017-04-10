package com.softwareacademy.searchandnavigate.map.mvp;

import com.softwareacademy.searchandnavigate.model.dto.PlacesDto;
import com.softwareacademy.searchandnavigate.model.dto.SearchParamsDto;
import com.softwareacademy.searchandnavigate.utils.ClearSubscriptions;

import java.util.List;

import io.reactivex.Observable;

/**
 *
 */

public interface MapsMVP {

    interface Model {
        Observable<List<PlacesDto>> getPlacesDto(SearchParamsDto searchParamsDto);
    }

    interface Presenter extends ClearSubscriptions {
        void search(SearchParamsDto searchParamsDto);
    }

    interface View {
        void showPlaces(List<PlacesDto> placesDtos);
    }
}
