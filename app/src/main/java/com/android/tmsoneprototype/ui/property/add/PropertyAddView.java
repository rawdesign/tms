package com.android.tmsoneprototype.ui.property.add;

import com.android.tmsoneprototype.api.data.PropertyAddData;

import java.util.List;

public interface PropertyAddView {
    /**
     * Validate.
     */
    void onValidate(boolean valid);
    /**
     * Pre process.
     */
    void onPreProcess();
    /**
     * Success.
     */
    void onSuccess(List<PropertyAddData> data);
    /**
     * Failed.
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
    /**
     * Error image size.
     */
    void onErrorSizeImage();
    /**
     * Error image extension.
     */
    void onErrorExtensionImage();
}