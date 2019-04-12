package com.example.mutiaramobile.serviceprovider;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private static Retrofit retrofit = null;

    public static Retrofit getToken(){
        if (retrofit == null){
            MutiaraServiceProvider provider = new MutiaraServiceProvider();
            retrofit = new Retrofit.Builder()
                    .baseUrl(provider.getGlobalUrl())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
