package com.newbie.amien.projectbesar2.data.retrofit;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by amien on 29/12/16.
 */

public class GetUser {

    @SerializedName("jumlah")
    @Expose
    private Integer jumlah;
    @SerializedName("error")
    @Expose
    private Integer error;
    @SerializedName("user")
    @Expose
    private List<User> user = null;

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

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

}