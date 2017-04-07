package com.softwareacademy.searchandnavigate.map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.softwareacademy.searchandnavigate.R;
import com.softwareacademy.searchandnavigate.SearchApplication;
import com.softwareacademy.searchandnavigate.map.dagger.DaggerMapActivityComponent;
import com.softwareacademy.searchandnavigate.map.dagger.MapActivityModule;
import com.softwareacademy.searchandnavigate.map.mvp.MapSearchPlacesMVP;
import com.softwareacademy.searchandnavigate.model.dto.PlacesDto;
import com.softwareacademy.searchandnavigate.utils.ViewsUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback, MapSearchPlacesMVP.View {


    public static final String QUERY = "query";
    private Marker me;
    private List<Marker> markers = new ArrayList<>();
    private LatLngBounds.Builder builder;

    public static void openActivity(Activity activity) {
        Intent intent = new Intent(activity, MapActivity.class);
        activity.startActivity(intent);
    }

    private GoogleMap mMap;
    private EditText searchView;
    private Map<String, String> queryMap = new HashMap<>();
    @Inject
    MapSearchPlacesMVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerMapActivityComponent.builder()
                .searchComponent(((SearchApplication) getApplication()).getSearchComponent())
                .mapActivityModule(new MapActivityModule(this))
                .build().inject(this);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        searchView = ViewsUtils.findView(this, R.id.searchView);
        queryMap.put("radius", "50");
        queryMap.put("location", "52.2,21");
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        me = mMap.addMarker(new MarkerOptions().position(new LatLng(52.2, 21)).title(getString(R.string.here_i_am)));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(me.getPosition(), 14));


        mMap.setOnMapLongClickListener(latLng -> {
            removeMe();
            me = mMap.addMarker(new MarkerOptions().position(latLng).title(getString(R.string.here_i_am)));
            mMap.getUiSettings().setAllGesturesEnabled(false);
        });

        RxTextView.textChanges(searchView).
                subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribe(it -> {
                    if(it.length()>0) {
                        queryMap.put(QUERY, it.toString());
                        presenter.askForPlaces(queryMap);
                    }
                }, e -> {
                    e.printStackTrace();
                });
    }

    private void removeMe() {
        if (me != null) {
            me.remove();
        }
    }

    @Override
    public void showPlacesOnMap(List<PlacesDto> placesDtos) {
        removeMarkersFromMap();
        builder = new LatLngBounds.Builder();
        for (PlacesDto placesDto : placesDtos) {
            MarkerOptions markerOption = new MarkerOptions()
                    .position(placesDto.getLatLong())
                    .title(placesDto.getPlaceName());
            markers.add(mMap.addMarker(markerOption));
            builder.include(markerOption.getPosition());

        }
        animateCamera();
    }

    private void removeMarkersFromMap() {
        List<Marker> insideMarker = new ArrayList<>(markers);
        for (Marker marker : insideMarker) {
            markers.remove(marker);
            marker.remove();
        }
    }

    private void animateCamera() {
        LatLngBounds latLngBounds = builder.build();
        int padding = 100;
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(latLngBounds, padding);
        try {
            mMap.animateCamera(cu);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

    }
}
