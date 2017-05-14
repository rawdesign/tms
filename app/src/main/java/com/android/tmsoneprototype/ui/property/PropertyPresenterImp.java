package com.android.tmsoneprototype.ui.property;

import android.app.Activity;

import com.android.tmsoneprototype.api.PropertyAPI;
import com.android.tmsoneprototype.api.data.PropertyAddData;
import com.android.tmsoneprototype.api.response.PropertyAddResponse;
import com.android.tmsoneprototype.db.model.PropertyAdd;
import com.android.tmsoneprototype.db.model.PropertyList;
import com.android.tmsoneprototype.db.repo.PropertyRepo;
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

    @Override
    public void insert(PropertyAdd propertyAdd) {
        if(Utils.haveNetworkConnection(mActivity)){
            //Create Upload Server Client
            PropertyAPI service = Retrofit.setup().create(PropertyAPI.class);

            //File creating from selected URL
            File file = new File(propertyAdd.getImg());

            //Create RequestBody instance from file
            RequestBody requestOwner = RequestBody.create(MediaType.parse("text/plain"), propertyAdd.getOwner());
            RequestBody requestTitle = RequestBody.create(MediaType.parse("text/plain"), propertyAdd.getTitle());
            RequestBody requestAddress = RequestBody.create(MediaType.parse("text/plain"), propertyAdd.getAddress());
            RequestBody requestPrice = RequestBody.create(MediaType.parse("text/plain"), propertyAdd.getPrice());
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
                            mView.onAddSuccess(data);
                            break;
                        case "400":
                            //failed
                            break;
                        case "413":
                            mView.onErrorSizeImage();
                            break;
                        case "415":
                            mView.onErrorExtensionImage();
                            break;
                        default:
                            //failed
                            break;
                    }
                }

                @Override
                public void onFailure(Call<PropertyAddResponse> call, Throwable t) {
                    // Log error here since request failed
                }
            });
        }
    }

}