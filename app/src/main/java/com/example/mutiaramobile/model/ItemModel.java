package com.example.mutiaramobile.model;

import com.example.mutiaramobile.model.PembayaranNota.NotaOrderProduksiModel;
import com.example.mutiaramobile.model.PembayaranNota.TerminNotaProduksiModel;
import com.example.mutiaramobile.model.PenerimaanBarang.PenerimaanBarangModel;
import com.example.mutiaramobile.model.PenerimaanBarang.TerimaBarangNotaModel;
import com.example.mutiaramobile.model.ReturnProduksi.ListBarangNotaProduksiModel;
import com.example.mutiaramobile.model.ReturnProduksi.ListNotaProduksiModel;
import com.example.mutiaramobile.model.ReturnProduksi.SupplierProduksiModel;
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

    //============================================================================

    @SerializedName("BarangPenerimaan")
    @Expose
    private List<TerimaBarangNotaModel> itemTerimaBarang = null;

    public List<TerimaBarangNotaModel> getItemTerimaBarang() {
        return itemTerimaBarang;
    }

    public void setItemTerimaBarang(List<TerimaBarangNotaModel> item) {
        this.itemTerimaBarang = item;
    }

    //============================================================================

    @SerializedName("NotaOrderProduksi")
    @Expose
    private List<NotaOrderProduksiModel> notaOrderProduksi = null;

    public List<NotaOrderProduksiModel> getNotaOrderProduksi() {
        return notaOrderProduksi;
    }

    public void setNotaOrderProduksiModel(List<NotaOrderProduksiModel> data) {
        this.notaOrderProduksi = data;
    }

    //============================================================================

    @SerializedName("ListTerminNota")
    @Expose
    private List<TerminNotaProduksiModel> listTerminNota = null;

    public List<TerminNotaProduksiModel> getListTerminNota() {
        return listTerminNota;
    }

    public void setListTerminNota(List<TerminNotaProduksiModel> listTerminNota) {
        this.listTerminNota = listTerminNota;
    }


    //================================================================================
    //========================= Return Order Produksi ================================
    //================================================================================
    @SerializedName("SupplierProduksi")
    @Expose
    private List<SupplierProduksiModel> supplierProduksi = null;

    public List<SupplierProduksiModel> getSupplierProduksi() {
        return supplierProduksi;
    }

    public void setSupplierProduksi(List<SupplierProduksiModel> supplierProduksi) {
        this.supplierProduksi = supplierProduksi;
    }

    @SerializedName("NotaProduksi")
    @Expose
    private List<ListNotaProduksiModel> notaProduksi = null;

    public List<ListNotaProduksiModel> getNotaProduksi() {
        return notaProduksi;
    }

    public void setNotaProduksi(List<ListNotaProduksiModel> notaProduksi) {
        this.notaProduksi = notaProduksi;
    }

    @SerializedName("ListBarangNotaProduksi")
    @Expose
    private List<ListBarangNotaProduksiModel> listBarangNotaProduksi = null;

    public List<ListBarangNotaProduksiModel> getListBarangNotaProduksi() {
        return listBarangNotaProduksi;
    }

    public void setListBarangNotaProduksi(List<ListBarangNotaProduksiModel> listBarangNotaProduksi) {
        this.listBarangNotaProduksi = listBarangNotaProduksi;
    }
}
