package com.android.tmsoneprototype.ui.property;

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
     * Failed.
     */
    void onFailed();
}