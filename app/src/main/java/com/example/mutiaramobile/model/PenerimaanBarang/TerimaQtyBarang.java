package com.example.mutiaramobile.model.PenerimaanBarang;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TerimaQtyBarang {
    @SerializedName("qtyTerimaBarang")
    @Expose
    private int qtyTerimaBarang;

    public int getQtyTerimaBarang() {
        return qtyTerimaBarang;
    }

    public void setQtyTerimaBarang(int qtyTerimaBarang) {
        this.qtyTerimaBarang = qtyTerimaBarang;
    }
}
