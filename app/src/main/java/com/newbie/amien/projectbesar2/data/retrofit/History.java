package com.newbie.amien.projectbesar2.data.retrofit;

/**
 * Created by amien on 08/01/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class History {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("id_kost")
    @Expose
    private String idKost;
    @SerializedName("id_user")
    @Expose
    private String idUser;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdKost() {
        return idKost;
    }

    public void setIdKost(String idKost) {
        this.idKost = idKost;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

}
