package com.example.mutiaramobile.serviceprovider;

import okhttp3.OkHttpClient;
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

    public static Retrofit getData(){
        retrofit = null;
        if (retrofit == null){
            MutiaraServiceProvider provider = new MutiaraServiceProvider();
            OkHttpClient baseOkHttpClient = new OkHttpClient();
            OkHttpClient client = baseOkHttpClient
                    .newBuilder()
                    .followRedirects(false)
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(provider.getGlobalUrl())
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
