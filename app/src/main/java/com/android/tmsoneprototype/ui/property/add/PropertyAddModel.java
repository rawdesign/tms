package com.android.tmsoneprototype.ui.property.add;

public class PropertyAddModel {

    private String id;
    private String owner;
    private String title;
    private String address;
    private String price;
    private String img;
    private String imgThmb;
    private String status;

    public PropertyAddModel(String id, String owner, String title, String address,
                            String price, String img, String imgThmb, String status) {
        this.id = id;
        this.owner = owner;
        this.title = title;
        this.address = address;
        this.price = price;
        this.img = img;
        this.imgThmb = imgThmb;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImgThmb() {
        return imgThmb;
    }

    public void setImgThmb(String imgThmb) {
        this.imgThmb = imgThmb;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}