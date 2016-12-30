package com.newbie.amien.projectbesar2.data.retrofit;

/**
 * Created by amien on 29/12/16.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pemilik {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("nama_pemilik")
    @Expose
    private String namaPemilik;
    @SerializedName("alamat_pemilik")
    @Expose
    private String alamatPemilik;
    @SerializedName("telepon")
    @Expose
    private String telepon;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    public Pemilik(){}

    public Pemilik(String id, String namaPemilik, String alamatPemilik, String telepon, String email, String password) {
        this.id = id;
        this.namaPemilik = namaPemilik;
        this.alamatPemilik = alamatPemilik;
        this.telepon = telepon;
        this.email = email;
        this.password = password;
    }

    public Pemilik(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public void setNamaPemilik(String namaPemilik) {
        this.namaPemilik = namaPemilik;
    }

    public String getAlamatPemilik() {
        return alamatPemilik;
    }

    public void setAlamatPemilik(String alamatPemilik) {
        this.alamatPemilik = alamatPemilik;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}