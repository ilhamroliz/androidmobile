package com.example.mutiaramobile.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PenerimaanBarangModel {
    @SerializedName("po_id")
    @Expose
    private Integer poId;
    @SerializedName("po_nota")
    @Expose
    private String poNota;
    @SerializedName("s_company")
    @Expose
    private String sCompany;
    @SerializedName("po_date")
    @Expose
    private String poDate;

    public Integer getPoId() {
        return poId;
    }

    public void setPoId(Integer poId) {
        this.poId = poId;
    }

    public String getPoNota() {
        return poNota;
    }

    public void setPoNota(String poNota) {
        this.poNota = poNota;
    }

    public String getSCompany() {
        return sCompany;
    }

    public void setSCompany(String sCompany) {
        this.sCompany = sCompany;
    }

    public String getPoDate() {
        return poDate;
    }

    public void setPoDate(String poDate) {
        this.poDate = poDate;
    }
}
