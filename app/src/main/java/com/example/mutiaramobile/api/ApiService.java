package com.example.mutiaramobile.api;

import com.example.mutiaramobile.model.TokenModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    @POST("oauth/token")
    @FormUrlEncoded
    Call<TokenModel> getToken(@Field("username") String username,
                         @Field("password") String password,
                         @Field("grant_type") String grant_type,
                         @Field("client_secret") String client_secret,
                         @Field("client_id") String client_id
    );
}
