package com.android.tmsoneprototype.api.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PropertyData {

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
    @SerializedName("property_status")
    @Expose
    private String propertyStatus;
    @SerializedName("property_create_date")
    @Expose
    private String propertyCreateDate;
    @SerializedName("property_modify_date")
    @Expose
    private String propertyModifyDate;

    public PropertyData(String propertyId, String propertyOwner, String propertyTitle,
                        String propertyAddress, String propertyPrice, String propertyImg,
                        String propertyImgThmb, String propertyStatus) {
        this.propertyId = propertyId;
        this.propertyOwner = propertyOwner;
        this.propertyTitle = propertyTitle;
        this.propertyAddress = propertyAddress;
        this.propertyPrice = propertyPrice;
        this.propertyImg = propertyImg;
        this.propertyImgThmb = propertyImgThmb;
        this.propertyStatus = propertyStatus;
    }

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

    public String getPropertyStatus() {
        return propertyStatus;
    }

    public void setPropertyStatus(String propertyStatus) {
        this.propertyStatus = propertyStatus;
    }

    public String getPropertyCreateDate() {
        return propertyCreateDate;
    }

    public void setPropertyCreateDate(String propertyCreateDate) {
        this.propertyCreateDate = propertyCreateDate;
    }

    public String getPropertyModifyDate() {
        return propertyModifyDate;
    }

    public void setPropertyModifyDate(String propertyModifyDate) {
        this.propertyModifyDate = propertyModifyDate;
    }

}