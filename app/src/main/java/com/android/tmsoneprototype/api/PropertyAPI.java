package com.android.tmsoneprototype.api;

import com.android.tmsoneprototype.api.response.PropertyAddResponse;
import com.android.tmsoneprototype.api.response.PropertyResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface PropertyAPI {
    /**
     * Insert property
     */
    @Multipart
    @POST("api_property.php?action=insert_data")
    Call<PropertyAddResponse> insert(
            @Part("owner") RequestBody owner,
            @Part("title") RequestBody title,
            @Part("address") RequestBody address,
            @Part("price") RequestBody price,
            @Part MultipartBody.Part file
    );
    /**
     * Fetch data property.
     */
    @GET("api_property.php?action=get_property")
    Call<PropertyResponse> data();
}