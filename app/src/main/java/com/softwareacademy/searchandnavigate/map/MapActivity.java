package com.softwareacademy.searchandnavigate.map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.softwareacademy.searchandnavigate.R;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {


    private Marker me;

    public static void openActivity(Activity activity){
        Intent intent =  new Intent(activity,MapActivity.class);
        activity.startActivity(intent);
    }
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        me = mMap.addMarker(new MarkerOptions().position(new LatLng(52.2,21)).title(getString(R.string.here_i_am)));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(me.getPosition(), 14));


        mMap.setOnMapLongClickListener(latLng -> {
            removeMe();
            me = mMap.addMarker(new MarkerOptions().position(latLng).title(getString(R.string.here_i_am)));
            mMap.getUiSettings().setAllGesturesEnabled(false);
        });
    }

    private void removeMe() {
        if (me != null) {
            me.remove();
        }
    }
}
