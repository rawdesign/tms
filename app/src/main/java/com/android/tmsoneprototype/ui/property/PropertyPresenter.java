package com.android.tmsoneprototype.ui.property;

import com.android.tmsoneprototype.db.model.PropertyAdd;

public interface PropertyPresenter {
    /**
     * Load data.
     */
    void loadData();
    /**
     * Insert property to server
     */
    void insert(PropertyAdd propertyAdd);
}