package com.softwareacademy.searchandnavigate.model.dto;

import com.google.android.gms.maps.model.LatLng;
import com.softwareacademy.searchandnavigate.model.json.Result;

/**
 *
 */

public class PlacesDto {

    private String placeName;
    private String photoUrl;
    private double latitude;
    private double longitude;

    public PlacesDto(Result result) {

        this.placeName = result.getName();

        this.photoUrl = result.getIcon();
        this.latitude = result.getGeometry().getLocation().getLat();
        this.longitude = result.getGeometry().getLocation().getLng();

    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public LatLng getLatLong() {
        return new LatLng(latitude, longitude);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlacesDto)) return false;

        PlacesDto placesDto = (PlacesDto) o;

        if (Double.compare(placesDto.latitude, latitude) != 0) return false;
        if (Double.compare(placesDto.longitude, longitude) != 0) return false;
        if (placeName != null ? !placeName.equals(placesDto.placeName) : placesDto.placeName != null)
            return false;
        return photoUrl != null ? photoUrl.equals(placesDto.photoUrl) : placesDto.photoUrl == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = placeName != null ? placeName.hashCode() : 0;
        result = 31 * result + (photoUrl != null ? photoUrl.hashCode() : 0);
        temp = Double.doubleToLongBits(latitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
