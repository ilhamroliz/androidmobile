package com.example.mutiaramobile.api;

import com.example.mutiaramobile.model.ItemModel;
import com.example.mutiaramobile.model.ItemTerimaBarangModel;
import com.example.mutiaramobile.model.TokenModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
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

    @POST("api/penerimaan-barang/get-data-penerimaan")
    Call<ItemModel> getDataPenerimaan(@Header("Authorization") String Authorization);

    @POST("api/penerimaan-barang/get-data-penerimaan/nota")
    @FormUrlEncoded
    Call<ItemTerimaBarangModel> getDataPenerimaanNota(@Header("Authorization") String Authorization,
                                          @Field("nota") String nota);
}
