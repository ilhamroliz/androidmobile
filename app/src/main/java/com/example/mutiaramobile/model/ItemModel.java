package com.example.mutiaramobile.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemModel {

    @SerializedName("item")
    @Expose
    private List<PenerimaanBarangModel> item = null;

    public List<PenerimaanBarangModel> getItem() {
        return item;
    }

    public void setItem(List<PenerimaanBarangModel> item) {
        this.item = item;
    }
}
