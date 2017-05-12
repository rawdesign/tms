package com.android.tmsoneprototype.ui.property.add;

public interface PropertyAddPresenter {
    /**
     * Close.
     */
    void close();
    /**
     * Validate.
     */
    void validate(String owner, boolean isUpload, String title, String address, String price);
    /**
     * Submit
     */
    void submit(String owner, String title, String address, String price, String image);
}