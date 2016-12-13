package com.newbie.amien.projectbesar2.Home.model;

/**
 * Created by amien on 12/12/16.
 */

public class Kost {
    public String id;
    public String nama_kost;
    public String alamat;
    public String fasilias;
    public String harga;
    public String keterangan;
    public String jumlah_kamar;
    public String tersedia;
    public String longtitude;
    public String latitude;
    public String image;

    public Kost(String id, String nama_kost, String alamat, String fasilias, String harga, String keterangan, String jumlah_kamar, String tersedia, String longtitude, String latitude, String image) {
        this.id = id;
        this.nama_kost = nama_kost;
        this.alamat = alamat;
        this.fasilias = fasilias;
        this.harga = harga;
        this.keterangan = keterangan;
        this.jumlah_kamar = jumlah_kamar;
        this.tersedia = tersedia;
        this.longtitude = longtitude;
        this.latitude = latitude;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama_kost() {
        return nama_kost;
    }

    public void setNama_kost(String nama_kost) {
        this.nama_kost = nama_kost;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getFasilias() {
        return fasilias;
    }

    public void setFasilias(String fasilias) {
        this.fasilias = fasilias;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getJumlah_kamar() {
        return jumlah_kamar;
    }

    public void setJumlah_kamar(String jumlah_kamar) {
        this.jumlah_kamar = jumlah_kamar;
    }

    public String getTersedia() {
        return tersedia;
    }

    public void setTersedia(String tersedia) {
        this.tersedia = tersedia;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
