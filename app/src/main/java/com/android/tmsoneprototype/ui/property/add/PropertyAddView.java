package com.android.tmsoneprototype.ui.property.add;

import com.android.tmsoneprototype.db.model.PropertyAdd;

public interface PropertyAddView {
    /**
     * Validate.
     */
    void onValidate(boolean valid);
    /**
     * Success.
     */
    void onSuccess(PropertyAdd propertyAdd);
    /**
     * Failed.
     */
    void onFailed();
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