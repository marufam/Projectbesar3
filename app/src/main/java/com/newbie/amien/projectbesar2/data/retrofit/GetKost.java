package com.newbie.amien.projectbesar2.data.retrofit;

/**
 * Created by amien on 28/12/16.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetKost {

    @SerializedName("jumlah")
    @Expose
    private Integer jumlah;
    @SerializedName("error")
    @Expose
    private Integer error;
    @SerializedName("kost")
    @Expose
    private List<Kost> kost = null;

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public List<Kost> getKost() {
        return kost;
    }

    public void setKost(List<Kost> kost) {
        this.kost = kost;
    }

}