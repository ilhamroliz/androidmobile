package com.example.mutiaramobile.model;

import com.example.mutiaramobile.model.PenerimaanBarang.TerimaBarangNotaModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemTerimaBarangModel {
    @SerializedName("item")
    @Expose
    private List<TerimaBarangNotaModel> itemTerimaBarang = null;

    public List<TerimaBarangNotaModel> getItemTerimaBarang() {
        return itemTerimaBarang;
    }

    public void setItemTerimaBarang(List<TerimaBarangNotaModel> item) {
        this.itemTerimaBarang = item;
    }
}
