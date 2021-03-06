package com.example.mutiaramobile.api;

import com.example.mutiaramobile.model.ItemModel;
import com.example.mutiaramobile.model.PenerimaanBarang.TerimaQtyBarangModel;
import com.example.mutiaramobile.model.ReturnProduksi.SupplierProduksiModel;
import com.example.mutiaramobile.model.StatusModel;
import com.example.mutiaramobile.model.TokenModel;

import java.util.Calendar;

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
                         @Field("client_id") String client_id);

    @POST("api/penerimaan-barang/get-data-penerimaan")
    Call<ItemModel> getDataPenerimaan(@Header("Authorization") String Authorization);

    @POST("api/penerimaan-barang/get-data-penerimaan/nota")
    @FormUrlEncoded
    Call<ItemModel> getDataPenerimaanNota(@Header("Authorization") String Authorization,
                                          @Field("nota") String nota);

    @POST("api/penerimaan-barang/get-data-penerimaan/nota-item")
    @FormUrlEncoded
    Call<TerimaQtyBarangModel> getQtyBarang(@Header("Authorization") String Authorization,
                                            @Field("item") Integer item,
                                            @Field("nota") String nota);

    @POST("api/penerimaan-barang/get-data-penerimaan/terima-item")
    @FormUrlEncoded
    Call<StatusModel> simpanTerimaBarang(@Header("Authorization") String Authorization,
                                         @Field("item") Integer item,
                                         @Field("satuan") Integer satuan,
                                         @Field("qty") Integer qty,
                                         @Field("username") String username,
                                         @Field("notado") String notado,
                                         @Field("nota") String nota);

    @POST("api/pembayaran-nota/get-data-supplier")
    Call<ItemModel> getNotaPembayaran(@Header("Authorization") String Authorization);

    @POST("api/pembayaran-nota/get-data-termin")
    @FormUrlEncoded
    Call<ItemModel> getTerminProduksi(@Header("Authorization") String Authorization,
                                      @Field("nota") String nota);

    @POST("api/pembayaran-nota/update-data-termin")
    @FormUrlEncoded
    Call<StatusModel> simpanDataTermin(@Header("Authorization") String Authorization,
                                       @Field("nota") String nota,
                                       @Field("termin") String termin,
                                       @Field("bayar") Integer bayar);

    @POST("api/return-produksi/get-data-supplier")
    Call<ItemModel> getSupplierProduksi(@Header("Authorization") String Authorization);

    @POST("api/return-produksi/get-nota-produksi")
    @FormUrlEncoded
    Call<ItemModel> getListNotaProduksi(@Header("Authorization") String Authorization,
                                        @Field("supplier") Integer supplier,
                                        @Field("tglawal") String tglawal,
                                        @Field("tglakhir") String tglakhir);

    @POST("api/return-produksi/get-item-nota")
    @FormUrlEncoded
    Call<ItemModel> getListBarangNotaProduksi(@Header("Authorization") String Authorization,
                                              @Field("nota") String nota);
}
