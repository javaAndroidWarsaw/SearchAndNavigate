package com.softwareacademy.searchandnavigate.map.mvp;

import com.softwareacademy.searchandnavigate.utils.network.AbstractClearSubscribtion;
import com.softwareacademy.searchandnavigate.utils.network.SchedulersUtils;

import java.util.Map;

/**
 *
 */

public class MapSearchPresenter extends AbstractClearSubscribtion implements MapSearchPlacesMVP.Presenter {
    private MapSearchPlacesMVP.Model model;
    private MapSearchPlacesMVP.View view;
    private SchedulersUtils schedulersUtils;

    public MapSearchPresenter(MapSearchPlacesMVP.Model model, MapSearchPlacesMVP.View view, SchedulersUtils schedulersUtils) {
        this.model = model;
        this.view = view;
        this.schedulersUtils = schedulersUtils;
    }

    @Override
    public void askForPlaces(Map<String, String> queryMap) {
        addToSubsctibiton(
                model.getPlaces(queryMap)
                .subscribeOn(schedulersUtils.subscribeScheduler())
                .observeOn(schedulersUtils.observScheduler())
                .subscribe(it->view.showPlacesOnMap(it), Throwable::printStackTrace)
        );
    }
}
