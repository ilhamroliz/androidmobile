package com.example.mutiaramobile.model.ReturnProduksi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SupplierProduksiModel {
    @SerializedName("s_name")
    @Expose
    private String sName;
    @SerializedName("s_company")
    @Expose
    private String sCompany;
    @SerializedName("s_id")
    @Expose
    private int sId;

    public String getSName() {
        return sName;
    }

    public void setSName(String sName) {
        this.sName = sName;
    }

    public String getSCompany() {
        return sCompany;
    }

    public void setSCompany(String sCompany) {
        this.sCompany = sCompany;
    }

    public int getSId() {
        return sId;
    }

    public void setSId(int sId) {
        this.sId = sId;
    }
}
