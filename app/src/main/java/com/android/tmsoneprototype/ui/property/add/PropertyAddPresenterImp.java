package com.android.tmsoneprototype.ui.property.add;

import android.app.Activity;

import com.android.tmsoneprototype.db.model.PropertyAdd;
import com.android.tmsoneprototype.db.repo.PropertyRepo;
import com.android.tmsoneprototype.util.Utils;

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
        int result;
        PropertyRepo repo = new PropertyRepo();

        //Insert data
        PropertyAdd obj = new PropertyAdd();
        obj.setId(Utils.getImageUUID());
        obj.setOwner(owner);
        obj.setTitle(title);
        obj.setAddress(address);
        obj.setPrice(price);
        obj.setImg(image);
        obj.setImgThmb(image);
        obj.setStatus("pending");
        obj.setCreateDate(Utils.nowDateComplete());
        result = repo.insert(obj);

        if(result >= 1){
            mView.onSuccess(obj);
        }else{
            mView.onFailed();
        }
    }

}