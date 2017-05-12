package com.android.tmsoneprototype.api.response;

import com.android.tmsoneprototype.api.data.PropertyAddData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PropertyAddResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<PropertyAddData> data = null;

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

    public List<PropertyAddData> getData() {
        return data;
    }

    public void setData(List<PropertyAddData> data) {
        this.data = data;
    }

}