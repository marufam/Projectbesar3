package com.newbie.amien.projectbesar2.data.retrofit;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by amien on 29/12/16.
 */

public class GetPemilik {

        @SerializedName("jumlah")
        @Expose
        private Integer jumlah;
        @SerializedName("error")
        @Expose
        private Integer error;
        @SerializedName("pemilik")
        @Expose
        private List<Pemilik> pemilik = null;

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

        public List<Pemilik> getPemilik() {
            return pemilik;
        }

        public void setPemilik(List<Pemilik> pemilik) {
            this.pemilik = pemilik;
        }

    }

