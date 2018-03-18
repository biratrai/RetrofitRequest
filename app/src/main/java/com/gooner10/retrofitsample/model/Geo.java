package com.gooner10.retrofitsample.model;

import com.google.gson.annotations.SerializedName;

/**
 * * Model for User Geo
 */

public class Geo {
    @SerializedName("lat")
    private String lat;

    @SerializedName("lng")
    private String lng;

    /**
     * @return The lat
     */
    public String getLat() {
        return lat;
    }

    /**
     * @param lat The lat
     */
    public void setLat(String lat) {
        this.lat = lat;
    }

    /**
     * @return The lng
     */
    public String getLng() {
        return lng;
    }

    /**
     * @param lng The lng
     */
    public void setLng(String lng) {
        this.lng = lng;
    }

}
