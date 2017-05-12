package com.android.tmsoneprototype.ui.property.add;

import android.app.Activity;

import com.android.tmsoneprototype.api.PropertyAPI;
import com.android.tmsoneprototype.api.data.PropertyAddData;
import com.android.tmsoneprototype.api.response.PropertyAddResponse;
import com.android.tmsoneprototype.service.Retrofit;
import com.android.tmsoneprototype.util.Utils;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        mView.onPreProcess();
        if(Utils.haveNetworkConnection(mActivity)){
            //Create Upload Server Client
            PropertyAPI service = Retrofit.setup().create(PropertyAPI.class);

            //File creating from selected URL
            File file = new File(image);

            //Create RequestBody instance from file
            RequestBody requestOwner = RequestBody.create(MediaType.parse("text/plain"), owner);
            RequestBody requestTitle = RequestBody.create(MediaType.parse("text/plain"), title);
            RequestBody requestAddress = RequestBody.create(MediaType.parse("text/plain"), address);
            RequestBody requestPrice = RequestBody.create(MediaType.parse("text/plain"), price);
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

            //MultipartBody.Part is used to send also the actual file name
            MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);

            Call<PropertyAddResponse> call = service.insert(requestOwner, requestTitle, requestAddress, requestPrice, body);
            call.enqueue(new Callback<PropertyAddResponse>() {
                @Override
                public void onResponse(Call<PropertyAddResponse> call, Response<PropertyAddResponse> response) {
                    String status = response.body().getStatus();
                    switch (status) {
                        case "200":
                            List<PropertyAddData> data = response.body().getData();
                            mView.onSuccess(data);
                            break;
                        case "400":
                            mView.onFailed();
                            break;
                        case "413":
                            mView.onErrorSizeImage();
                            break;
                        case "415":
                            mView.onErrorExtensionImage();
                            break;
                        default:
                            mView.onFailed();
                            break;
                    }
                }

                @Override
                public void onFailure(Call<PropertyAddResponse> call, Throwable t) {
                    // Log error here since request failed
                }
            });
        }else{
            mView.onInternetFailed();
        }
    }

}