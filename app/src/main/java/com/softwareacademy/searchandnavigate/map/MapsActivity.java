package com.softwareacademy.searchandnavigate.map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

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

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker warsawMarker;


    public static void openMap(Activity activity) {
        Intent intent = new Intent(activity, MapsActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        findViewById(R.id.resetMarkers).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (warsawMarker != null) {
                    warsawMarker.remove();
                }
                if (markers != null) {
                    for (Marker marker : markers) {
                        marker.remove();
                    }
                }
                markers = new ArrayList<Marker>();

            }
        });


        findViewById(R.id.showMarkers).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });

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


    }
}
