package com.example.mutiaramobile.model.PembayaranNota;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotaOrderProduksiModel {
    @SerializedName("s_name")
    @Expose
    private String sName;
    @SerializedName("pop_date")
    @Expose
    private String popDate;
    @SerializedName("pop_value")
    @Expose
    private String popValue;
    @SerializedName("po_nota")
    @Expose
    private String poNota;

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getPoNota() {
        return poNota;
    }

    public void setPoNota(String poNota) {
        this.poNota = poNota;
    }

    public String getSName() {
        return sName;
    }

    public void setSName(String sName) {
        this.sName = sName;
    }

    public String getPopDate() {
        return popDate;
    }

    public void setPopDate(String popDate) {
        this.popDate = popDate;
    }

    public String getPopValue() {
        return popValue;
    }

    public void setPopValue(String popValue) {
        this.popValue = popValue;
    }
}
