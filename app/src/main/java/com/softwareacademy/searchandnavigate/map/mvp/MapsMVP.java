package com.softwareacademy.searchandnavigate.map.mvp;

import com.softwareacademy.searchandnavigate.model.dto.PlacesDto;

import java.util.List;

import io.reactivex.Observable;

/**
 *
 */

public interface MapsMVP {

    interface Model {
        Observable<List<PlacesDto>> getPlacesDto();
    }

    interface Presenter {
    }

    interface View {
    }
}
