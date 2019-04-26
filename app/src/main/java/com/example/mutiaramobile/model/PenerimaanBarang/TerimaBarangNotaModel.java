package com.example.mutiaramobile.model.PenerimaanBarang;

import android.content.ClipData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TerimaBarangNotaModel {
    @SerializedName("po_nota")
    @Expose
    private String poNota;
    @SerializedName("i_name")
    @Expose
    private String iName;
    @SerializedName("u_name")
    @Expose
    private String uName;
    @SerializedName("pod_qty")
    @Expose
    private int podQty;
    @SerializedName("i_id")
    @Expose
    private int i_id;

    public String getPoNota() {
        return poNota;
    }

    public void setPoNota(String poNota) {
        this.poNota = poNota;
    }

    public String getIName() {
        return iName;
    }

    public void setIName(String iName) {
        this.iName = iName;
    }

    public String getUName() {
        return uName;
    }

    public void setUName(String uName) {
        this.uName = uName;
    }

    public int getPodQty() {
        return podQty;
    }

    public void setPodQty(int podQty) {
        this.podQty = podQty;
    }

    public int getI_id() {
        return i_id;
    }

    public void setI_id(int i_id) {
        this.i_id = i_id;
    }

}
