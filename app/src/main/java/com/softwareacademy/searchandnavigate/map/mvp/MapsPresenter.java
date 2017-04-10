package com.softwareacademy.searchandnavigate.map.mvp;

import com.softwareacademy.searchandnavigate.model.dto.SearchParamsDto;
import com.softwareacademy.searchandnavigate.utils.AppLog;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
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
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(placesDtos -> {
                            MapsPresenter.this.view.showPlaces(placesDtos);
                            AppLog.log("AAA", "been there");
                        }
                        , error -> error.printStackTrace(), new Action() {
                            @Override
                            public void run() throws Exception {
                                AppLog.log("AAA", "been there");
                            }
                        });
    }

    @Override
    public void clearSubscriptions() {
        if (disposable != null) {
            disposable.dispose();
        }
    }


}
