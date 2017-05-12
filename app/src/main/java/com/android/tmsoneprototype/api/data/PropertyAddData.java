package com.android.tmsoneprototype.api.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PropertyAddData {

    @SerializedName("property_id")
    @Expose
    private String propertyId;
    @SerializedName("property_owner")
    @Expose
    private String propertyOwner;
    @SerializedName("property_title")
    @Expose
    private String propertyTitle;
    @SerializedName("property_address")
    @Expose
    private String propertyAddress;
    @SerializedName("property_price")
    @Expose
    private String propertyPrice;
    @SerializedName("property_img")
    @Expose
    private String propertyImg;
    @SerializedName("property_img_thmb")
    @Expose
    private String propertyImgThmb;

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyOwner() {
        return propertyOwner;
    }

    public void setPropertyOwner(String propertyOwner) {
        this.propertyOwner = propertyOwner;
    }

    public String getPropertyTitle() {
        return propertyTitle;
    }

    public void setPropertyTitle(String propertyTitle) {
        this.propertyTitle = propertyTitle;
    }

    public String getPropertyAddress() {
        return propertyAddress;
    }

    public void setPropertyAddress(String propertyAddress) {
        this.propertyAddress = propertyAddress;
    }

    public String getPropertyPrice() {
        return propertyPrice;
    }

    public void setPropertyPrice(String propertyPrice) {
        this.propertyPrice = propertyPrice;
    }

    public String getPropertyImg() {
        return propertyImg;
    }

    public void setPropertyImg(String propertyImg) {
        this.propertyImg = propertyImg;
    }

    public String getPropertyImgThmb() {
        return propertyImgThmb;
    }

    public void setPropertyImgThmb(String propertyImgThmb) {
        this.propertyImgThmb = propertyImgThmb;
    }

}