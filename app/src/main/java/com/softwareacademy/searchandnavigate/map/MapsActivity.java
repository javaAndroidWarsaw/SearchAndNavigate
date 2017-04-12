package com.softwareacademy.searchandnavigate.map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.softwareacademy.searchandnavigate.R;
import com.softwareacademy.searchandnavigate.SearchApplication;
import com.softwareacademy.searchandnavigate.dagger.SearchComponent;
import com.softwareacademy.searchandnavigate.map.dagger.DaggerMapsActivityComponent;
import com.softwareacademy.searchandnavigate.map.dagger.MapsActivityModule;
import com.softwareacademy.searchandnavigate.map.mvp.MapsMVP;
import com.softwareacademy.searchandnavigate.model.dto.PlacesDto;
import com.softwareacademy.searchandnavigate.model.dto.SearchParamsDto;
import com.softwareacademy.searchandnavigate.views.SearchQueryView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, MapsMVP.View {

    public static final int CODE = 1234;
    private GoogleMap mMap;
    private Marker warsawMarker;
    private SearchQueryView queryView;

    @Inject
    MapsMVP.Presenter presenter;


    public static void openMap(Activity activity) {
        Intent intent = new Intent(activity, MapsActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SearchComponent searchComponent = ((SearchApplication) getApplication()).getSearchComponent();
        DaggerMapsActivityComponent.builder()
                .searchComponent(searchComponent)
                .mapsActivityModule(new MapsActivityModule(this))
                .build().inject(this);

        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        queryView = (SearchQueryView) findViewById(R.id.search_widget);

        queryView.subscribeToStream()
                .debounce(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(searchParamsDto -> {
                    presenter.search(searchParamsDto);
                }, error -> {
                    error.printStackTrace();
                });


    }

    private void showAllMarkers() {
        builder = LatLngBounds.builder();
        if (markers != null && markers.size() > 0) {
            for (Marker marker : markers) {
                builder.include(marker.getPosition());
            }

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

    private List<Marker> markers;
    private LatLngBounds.Builder builder;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng warsawLatLng = new LatLng(52.2, 21);
        warsawMarker = mMap.addMarker(new MarkerOptions().position(warsawLatLng).title("Marker in Warsaw"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(warsawLatLng, 14));
        markers = new ArrayList<>();
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                Marker marker = mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title(latLng.toString()));
                markers.add(marker);

            }
        });

        HashMap<String, String> querys = new HashMap<>();
        querys.put("query", "Centrum konferencyjne kopernik");
        querys.put("location", "52.2,21");
        presenter.search(new SearchParamsDto("textsearch", querys));

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void showPlaces(List<PlacesDto> placesDtos) {
        if (placesDtos.size() > 0)
            for (PlacesDto dto : placesDtos) {
                markers.add(mMap.addMarker(new MarkerOptions()
                        .position(dto.getLatLong())
                        .title(dto.getTitle())));
            }
        showAllMarkers();
    }
}
