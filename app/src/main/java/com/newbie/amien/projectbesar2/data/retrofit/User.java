package com.newbie.amien.projectbesar2.data.retrofit;

/**
 * Created by amien on 29/12/16.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("nama_user")
    @Expose
    private String namaUser;
    @SerializedName("alamat_user")
    @Expose
    private String alamatUser;
    @SerializedName("telepon")
    @Expose
    private String telepon;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;

    public User(String id, String namaUser, String alamatUser, String telepon, String email, String password) {
        this.id = id;
        this.namaUser = namaUser;
        this.alamatUser = alamatUser;
        this.telepon = telepon;
        this.email = email;
        this.password = password;
    }

    public User(String namaUser, String alamatUser, String telepon, String email, String password) {
        this.namaUser = namaUser;
        this.alamatUser = alamatUser;
        this.telepon = telepon;
        this.email = email;
        this.password = password;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    public String getAlamatUser() {
        return alamatUser;
    }

    public void setAlamatUser(String alamatUser) {
        this.alamatUser = alamatUser;
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