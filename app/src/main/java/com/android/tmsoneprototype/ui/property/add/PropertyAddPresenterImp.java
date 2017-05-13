package com.android.tmsoneprototype.ui.property.add;

import android.app.Activity;

import com.android.tmsoneprototype.ui.property.PropertyFragment;

public class PropertyAddPresenterImp implements PropertyAddPresenter {

    private PropertyAddView mView;
    private Activity mActivity;

    public PropertyAddPresenterImp(PropertyAddView mView, Activity mActivity) {
        this.mView = mView;
        this.mActivity = mActivity;
    }

    @Override
    public void close() {
        mActivity.finish();
    }

    @Override
    public void validate(String owner, boolean isUpload, String title, String address, String price) {
        boolean valid = true;

        if (owner.isEmpty()) {
            valid = false;
            mView.onErrorEmptyOwner();
        } else if (!isUpload) {
            valid = false;
            mView.onErrorEmptyImage();
        } else if (title.isEmpty()) {
            valid = false;
            mView.onErrorEmptyTitle();
        } else if (address.isEmpty()) {
            valid = false;
            mView.onErrorEmptyAddress();
        } else if (price.isEmpty()) {
            valid = false;
            mView.onErrorEmptyPrice();
        }

        mView.onValidate(valid);
    }

    @Override
    public void submit(String owner, String title, String address, String price, String image) {
        close();
        PropertyFragment propertyFragment = new PropertyFragment();
        propertyFragment.addItem(new PropertyAddModel("0", owner, title, address, price, image, image, "pending"));
    }

}