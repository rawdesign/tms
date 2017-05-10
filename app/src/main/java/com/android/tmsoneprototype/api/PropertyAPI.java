package com.android.tmsoneprototype.api;

import com.android.tmsoneprototype.api.response.PropertyResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PropertyAPI {
    /**
     * Fetch data property.
     */
    @GET("api_property.php?action=get_property")
    Call<PropertyResponse> data();
}