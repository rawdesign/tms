package com.android.tmsoneprototype.ui.property.add;

public interface PropertyAddView {
    /**
     * Validate.
     */
    void onValidate(boolean valid);
    /**
     * Post pre process.
     */
    void onPreProcess();
    /**
     * Post success.
     */
    void onSuccess();
    /**
     * Post failed.
     */
    void onFailed();
    /**
     * Internet failed.
     */
    void onInternetFailed();
    /**
     * Error owner empty.
     */
    void onErrorEmptyOwner();
    /**
     * Error image empty.
     */
    void onErrorEmptyImage();
    /**
     * Error title empty.
     */
    void onErrorEmptyTitle();
    /**
     * Error address empty.
     */
    void onErrorEmptyAddress();
    /**
     * Error price empty.
     */
    void onErrorEmptyPrice();
}