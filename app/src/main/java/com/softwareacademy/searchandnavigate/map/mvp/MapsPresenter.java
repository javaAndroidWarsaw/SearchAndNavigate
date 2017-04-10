package com.softwareacademy.searchandnavigate.map.mvp;

import com.softwareacademy.searchandnavigate.model.dto.SearchParamsDto;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 *
 */

public class MapsPresenter implements MapsMVP.Presenter {

    private MapsMVP.Model model;
    private MapsMVP.View view;
    private Disposable disposable;

    public MapsPresenter(MapsMVP.Model model, MapsMVP.View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void search(SearchParamsDto searchParamsDto) {
        disposable = model.getPlacesDto(searchParamsDto)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(placesDtos -> MapsPresenter.this.view.showPlaces(placesDtos)
                        , error -> error.printStackTrace());
    }

    @Override
    public void clearSubscriptions() {
        if (disposable != null) {
            disposable.dispose();
        }
    }


}
