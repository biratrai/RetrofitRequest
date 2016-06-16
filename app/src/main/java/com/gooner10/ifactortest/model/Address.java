package com.gooner10.ifactortest.model;

import com.google.gson.annotations.SerializedName;

/**
 * * Model for User Address
 */
public class Address {

    @SerializedName("street")
    private String street;

    @SerializedName("suite")
    private String suite;

    @SerializedName("city")
    private String city;

    @SerializedName("zipcode")
    private String zipcode;

    @SerializedName("geo")
    private Geo geo;

    /**
     * @return The street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street The street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return The suite
     */
    public String getSuite() {
        return suite;
    }

    /**
     * @param suite The suite
     */
    public void setSuite(String suite) {
        this.suite = suite;
    }

    /**
     * @return The city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city The city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return The zipcode
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * @param zipcode The zipcode
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * @return The geo
     */
    public Geo getGeo() {
        return geo;
    }

    /**
     * @param geo The geo
     */
    public void setGeo(Geo geo) {
        this.geo = geo;
    }
}
