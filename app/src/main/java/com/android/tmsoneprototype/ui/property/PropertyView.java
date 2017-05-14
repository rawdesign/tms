package com.android.tmsoneprototype.ui.property;

import com.android.tmsoneprototype.api.data.PropertyAddData;
import com.android.tmsoneprototype.db.model.PropertyList;

import java.util.List;

public interface PropertyView {
    /**
     * Pre process.
     */
    void onPreProcess();
    /**
     * Success.
     */
    void onSuccess(List<PropertyList> data);
    /**
     * Success insert property
     */
    void onAddSuccess(List<PropertyAddData> data);
    /**
     * Failed.
     */
    void onFailed();
    /**
     * Error image size.
     */
    void onErrorSizeImage();
    /**
     * Error image extension.
     */
    void onErrorExtensionImage();
}