package com.newbie.amien.projectbesar2.data.retrofit;

/**
 * Created by amien on 08/01/17.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetHistory {

    @SerializedName("jumlah")
    @Expose
    private Integer jumlah;
    @SerializedName("error")
    @Expose
    private Integer error;
    @SerializedName("history")
    @Expose
    private List<History> history = null;

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

    public List<History> getHistory() {
        return history;
    }

    public void setHistory(List<History> history) {
        this.history = history;
    }

}