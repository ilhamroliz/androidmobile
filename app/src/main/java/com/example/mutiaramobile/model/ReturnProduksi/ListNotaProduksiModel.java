package com.example.mutiaramobile.model.ReturnProduksi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListNotaProduksiModel {
    @SerializedName("po_nota")
    @Expose
    private String poNota;
    @SerializedName("po_date")
    @Expose
    private String poDate;
    @SerializedName("jumlah")
    @Expose
    private String jumlah;

    public String getPoNota() {
        return poNota;
    }

    public void setPoNota(String poNota) {
        this.poNota = poNota;
    }

    public String getPoDate() {
        return poDate;
    }

    public void setPoDate(String poDate) {
        this.poDate = poDate;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }
}
