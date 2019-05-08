package com.example.mutiaramobile.model.PembayaranNota;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TerminNotaProduksiModel {
    @SerializedName("pop_termin")
    @Expose
    private String popTermin;
    @SerializedName("pop_datetop")
    @Expose
    private String popDatetop;
    @SerializedName("pop_value")
    @Expose
    private String popValue;
    @SerializedName("pop_valueint")
    @Expose
    private String popValueint;
    @SerializedName("tagihan")
    @Expose
    private String tagihan;
    @SerializedName("tagihanrp")
    @Expose
    private String tagihanrp;

    public String getPopTermin() {
        return popTermin;
    }

    public void setPopTermin(String popTermin) {
        this.popTermin = popTermin;
    }

    public String getPopDatetop() {
        return popDatetop;
    }

    public void setPopDatetop(String popDatetop) {
        this.popDatetop = popDatetop;
    }

    public String getPopValue() {
        return popValue;
    }

    public void setPopValue(String popValue) {
        this.popValue = popValue;
    }

    public String getPopValueint() {
        return popValueint;
    }

    public void setPopValueint(String popValueint) {
        this.popValueint = popValueint;
    }

    public String getTagihan() {
        return tagihan;
    }

    public void setTagihan(String tagihan) {
        this.tagihan = tagihan;
    }

    public String getTagihanrp() {
        return tagihanrp;
    }

    public void setTagihanrp(String tagihanrp) {
        this.tagihanrp = tagihanrp;
    }
}
