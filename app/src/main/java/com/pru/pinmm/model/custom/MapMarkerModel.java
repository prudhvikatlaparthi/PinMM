package com.pru.pinmm.model.custom;

import com.google.android.gms.maps.model.LatLng;

public class MapMarkerModel {

    private LatLng latLng;
    private String stopName;

    public MapMarkerModel(LatLng latLng, String stopName) {
        this.latLng = latLng;
        this.stopName = stopName;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }
}
