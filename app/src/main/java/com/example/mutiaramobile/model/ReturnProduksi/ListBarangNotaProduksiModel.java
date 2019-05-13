package com.example.mutiaramobile.model.ReturnProduksi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListBarangNotaProduksiModel {
    @SerializedName("i_name")
    @Expose
    private String iName;
    @SerializedName("u_name")
    @Expose
    private String uName;
    @SerializedName("pod_qty")
    @Expose
    private String podQty;

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

    public String getPodQty() {
        return podQty;
    }

    public void setPodQty(String podQty) {
        this.podQty = podQty;
    }
}
