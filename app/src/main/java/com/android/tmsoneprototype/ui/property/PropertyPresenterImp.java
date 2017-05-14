package com.android.tmsoneprototype.ui.property;

import android.app.Activity;

import com.android.tmsoneprototype.db.model.PropertyList;
import com.android.tmsoneprototype.db.repo.PropertyRepo;

import java.util.List;

public class PropertyPresenterImp implements PropertyPresenter {

    private PropertyView mView;
    private Activity mActivity;

    public PropertyPresenterImp(PropertyView propertyView, Activity activity) {
        this.mView = propertyView;
        this.mActivity = activity;
    }

    @Override
    public void loadData() {
        mView.onPreProcess();
        PropertyRepo repo = new PropertyRepo();
        List<PropertyList> propertyLists = repo.getAll();

        if(propertyLists != null && !propertyLists.isEmpty()){
            mView.onSuccess(propertyLists);
        }else{
            mView.onFailed();
        }
    }

}