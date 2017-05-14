package com.android.tmsoneprototype.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.tmsoneprototype.api.PropertyAPI;
import com.android.tmsoneprototype.api.data.PropertyAddData;
import com.android.tmsoneprototype.api.response.PropertyAddResponse;
import com.android.tmsoneprototype.db.model.PropertyList;
import com.android.tmsoneprototype.db.repo.PropertyRepo;
import com.android.tmsoneprototype.ui.property.PropertyFragment;
import com.android.tmsoneprototype.util.Utils;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {
        if(Utils.haveNetworkConnection(context)){
            PropertyRepo repo = new PropertyRepo();
            List<PropertyList> propertyLists = repo.getPending();
            syncStatusProperty(context, propertyLists);
        }else{
            Utils.displayToast(context, "not connected", Toast.LENGTH_LONG);
        }
    }

    private void syncStatusProperty(final Context context, List<PropertyList> data) {
        if(data != null && !data.isEmpty()){
            for (int i = 0; i < data.size(); i++) {
                System.out.println("===============================");
                System.out.println("Id : " + data.get(i).getId());
                System.out.println("Owner : " + data.get(i).getOwner());
                System.out.println("Title : " + data.get(i).getTitle());
                System.out.println("Address : " + data.get(i).getAddress());
                System.out.println("Price : " + data.get(i).getPrice());
                System.out.println("Image : " + data.get(i).getImg());
                System.out.println("Create Date : " + data.get(i).getCreateDate());
                System.out.println("===============================");

                if(Utils.haveNetworkConnection(context)){
                    //Create Upload Server Client
                    PropertyAPI service = Retrofit.setup().create(PropertyAPI.class);

                    //File creating from selected URL
                    File file = new File(data.get(i).getImg());

                    //Create RequestBody instance from file
                    RequestBody requestOwner = RequestBody.create(MediaType.parse("text/plain"), data.get(i).getOwner());
                    RequestBody requestTitle = RequestBody.create(MediaType.parse("text/plain"), data.get(i).getTitle());
                    RequestBody requestAddress = RequestBody.create(MediaType.parse("text/plain"), data.get(i).getAddress());
                    RequestBody requestPrice = RequestBody.create(MediaType.parse("text/plain"), data.get(i).getPrice());
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
                                    int result;
                                    List<PropertyAddData> propertyAddDatas = response.body().getData();
                                    PropertyRepo repo = new PropertyRepo();
                                    result = repo.updateStatus(propertyAddDatas.get(0).getPropertyTitle(), "success");

                                    if(result >= 1){
                                        PropertyFragment propertyFragment = new PropertyFragment();
                                        propertyFragment.syncStatus(propertyAddDatas.get(0).getPropertyTitle());
                                    }else{
                                        //failed
                                    }
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
    }

}