package com.softwareacademy.searchandnavigate.model.dto;


import com.google.android.gms.maps.model.LatLng;
import com.softwareacademy.searchandnavigate.model.json.Result;

/**
 *
 */

public class PlacesDto {
    private String title;
    private double latitude,longitude;


    public PlacesDto(Result result) {
        this.title = result.getName();
        this.longitude = result.getGeometry().getLocation().getLng();
        this.latitude = result.getGeometry().getLocation().getLat();
    }

    public LatLng getLatLong(){
        return new LatLng(latitude,longitude);
    }
}
