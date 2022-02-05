package com.pru.pinmm.model.response;

import com.google.gson.annotations.SerializedName;

public class Job {
    @SerializedName("sessionToken")
    String sessionToken;
    @SerializedName("stopName")
    String stopName;
    @SerializedName("latitude")
    double latitude;
    @SerializedName("longitude")
    double longitude;

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }


    public double getLatitude() {
        return latitude;
    }

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
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
}
