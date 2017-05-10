package com.android.tmsoneprototype.ui.property;

import android.app.Activity;

import com.android.tmsoneprototype.api.PropertyAPI;
import com.android.tmsoneprototype.api.data.PropertyData;
import com.android.tmsoneprototype.api.response.PropertyResponse;
import com.android.tmsoneprototype.service.Retrofit;
import com.android.tmsoneprototype.util.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        if(Utils.haveNetworkConnection(mActivity)){
            PropertyAPI service = Retrofit.setup().create(PropertyAPI.class);
            Call<PropertyResponse> call = service.data();
            call.enqueue(new Callback<PropertyResponse>() {
                @Override
                public void onResponse(Call<PropertyResponse> call, Response<PropertyResponse> response) {
                    String status = response.body().getStatus();
                    if(status.equals("200")){
                        List<PropertyData> data = response.body().getData();
                        mView.onSuccess(data);
                    }else{
                        mView.onFailed();
                    }
                }

                @Override
                public void onFailure(Call<PropertyResponse> call, Throwable t) {
                    // Log error here since request failed
                }
            });
        }else{
            mView.onInternetFailed();
        }
    }

}