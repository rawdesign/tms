package com.android.tmsoneprototype.service;

import com.android.tmsoneprototype.util.Const;

import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit {

    public static retrofit2.Retrofit setup() {
        retrofit2.Retrofit retrofit = null;
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(Const.BASE_API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}