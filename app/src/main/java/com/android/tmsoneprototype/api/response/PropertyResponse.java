package com.android.tmsoneprototype.api.response;

import com.android.tmsoneprototype.api.data.PropertyData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PropertyResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("num_data")
    @Expose
    private Integer numData;
    @SerializedName("remaining")
    @Expose
    private Integer remaining;
    @SerializedName("data")
    @Expose
    private List<PropertyData> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getNumData() {
        return numData;
    }

    public void setNumData(Integer numData) {
        this.numData = numData;
    }

    public Integer getRemaining() {
        return remaining;
    }

    public void setRemaining(Integer remaining) {
        this.remaining = remaining;
    }

    public List<PropertyData> getData() {
        return data;
    }

    public void setData(List<PropertyData> data) {
        this.data = data;
    }

}