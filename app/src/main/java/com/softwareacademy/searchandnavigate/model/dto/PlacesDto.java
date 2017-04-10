package com.softwareacademy.searchandnavigate.model.dto;


import com.google.android.gms.maps.model.LatLng;
import com.softwareacademy.searchandnavigate.model.json.Result;

/**
 *
 */

public class PlacesDto {
    private String title;
    private double latitude,longitude;
    private String description;


    public PlacesDto(Result result) {
        this.title = result.getName();
        this.longitude = result.getLong();
        this.latitude = result.getLatitude();
        this.description = result.getReference();
    }

    public LatLng getLatLong(){
        return new LatLng(latitude,longitude);
    }

    public String getTitle() {
        return title;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlacesDto)) return false;

        PlacesDto placesDto = (PlacesDto) o;

        if (Double.compare(placesDto.latitude, latitude) != 0) return false;
        if (Double.compare(placesDto.longitude, longitude) != 0) return false;
        return title != null ? title.equals(placesDto.title) : placesDto.title == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = title != null ? title.hashCode() : 0;
        temp = Double.doubleToLongBits(latitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
