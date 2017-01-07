package com.newbie.amien.projectbesar2.data.retrofit;

/**
 * Created by amien on 28/12/16.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Kost {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("nama_kost")
    @Expose
    private String namaKost;
    @SerializedName("tipe_kost")
    @Expose
    private String tipeKost;
    @SerializedName("kota")
    @Expose
    private String kota;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("fasilitas")
    @Expose
    private String fasilitas;
    @SerializedName("harga")
    @Expose
    private String harga;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;
    @SerializedName("jumlah_kamar")
    @Expose
    private String jumlahKamar;
    @SerializedName("tersedia")
    @Expose
    private String tersedia;
    @SerializedName("longtitude")
    @Expose
    private String longtitude;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("nama_pemilik")
    @Expose
    private String namaPemilik;
    @SerializedName("telepon")
    @Expose
    private String telepon;

    public Kost(String id, String namaKost, String tipeKost, String kota, String alamat, String fasilitas, String harga, String keterangan, String jumlahKamar, String tersedia, String longtitude, String latitude, String image, String namaPemilik, String telepon) {
        this.id = id;
        this.namaKost = namaKost;
        this.tipeKost = tipeKost;
        this.kota = kota;
        this.alamat = alamat;
        this.fasilitas = fasilitas;
        this.harga = harga;
        this.keterangan = keterangan;
        this.jumlahKamar = jumlahKamar;
        this.tersedia = tersedia;
        this.longtitude = longtitude;
        this.latitude = latitude;
        this.image = image;
        this.namaPemilik = namaPemilik;
        this.telepon = telepon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaKost() {
        return namaKost;
    }

    public void setNamaKost(String namaKost) {
        this.namaKost = namaKost;
    }

    public String getTipeKost() {
        return tipeKost;
    }

    public void setTipeKost(String tipeKost) {
        this.tipeKost = tipeKost;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getFasilitas() {
        return fasilitas;
    }

    public void setFasilitas(String fasilitas) {
        this.fasilitas = fasilitas;
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

    public String getJumlahKamar() {
        return jumlahKamar;
    }

    public void setJumlahKamar(String jumlahKamar) {
        this.jumlahKamar = jumlahKamar;
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

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public void setNamaPemilik(String namaPemilik) {
        this.namaPemilik = namaPemilik;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

}