package com.example.mutiaramobile.model;

import com.example.mutiaramobile.model.PenerimaanBarang.PenerimaanBarangModel;
import com.example.mutiaramobile.model.PenerimaanBarang.TerimaBarangNotaModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemModel {

    @SerializedName("item")
    @Expose
    private List<PenerimaanBarangModel> itemPenerimaan = null;

    public List<PenerimaanBarangModel> getItemPenerimaan() {
        return itemPenerimaan;
    }

    public void setItemPenerimaan(List<PenerimaanBarangModel> item) {
        this.itemPenerimaan = item;
    }

    @SerializedName("BarangPenerimaan")
    @Expose
    private List<TerimaBarangNotaModel> itemTerimaBarang = null;

    public List<TerimaBarangNotaModel> getItemTerimaBarang() {
        return itemTerimaBarang;
    }

    public void setItemTerimaBarang(List<TerimaBarangNotaModel> item) {
        this.itemTerimaBarang = item;
    }

}
