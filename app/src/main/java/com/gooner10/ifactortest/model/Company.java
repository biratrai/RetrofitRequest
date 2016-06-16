package com.gooner10.ifactortest.model;

import com.google.gson.annotations.SerializedName;

/**
 * * Model for User Company
 */
public class Company {

    @SerializedName("name")
    private String name;

    @SerializedName("catchPhrase")
    private String catchPhrase;

    @SerializedName("bs")
    private String bs;

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The catchPhrase
     */
    public String getCatchPhrase() {
        return catchPhrase;
    }

    /**
     * @param catchPhrase The catchPhrase
     */
    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    /**
     * @return The bs
     */
    public String getBs() {
        return bs;
    }

    /**
     * @param bs The bs
     */
    public void setBs(String bs) {
        this.bs = bs;
    }
}

