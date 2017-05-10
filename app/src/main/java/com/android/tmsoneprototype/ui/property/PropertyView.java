package com.android.tmsoneprototype.ui.property;

import com.android.tmsoneprototype.api.data.PropertyData;

import java.util.List;

public interface PropertyView {
    /**
     * Pre process.
     */
    void onPreProcess();
    /**
     * Success.
     */
    void onSuccess(List<PropertyData> data);
    /**
     * Failed.
     */
    void onFailed();
    /**
     * Internet Failed.
     */
    void onInternetFailed();
}