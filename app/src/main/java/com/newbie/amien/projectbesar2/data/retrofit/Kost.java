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
    private String tipe_kost;
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
    @SerializedName("id_pemilik")
    @Expose
    private String idPemilik;

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

    public String getTipe_kost() {
        return tipe_kost;
    }

    public void setTipe_kost(String namaKost) {
        this.tipe_kost = tipe_kost;
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

    public String getIdPemilik() {
        return idPemilik;
    }

    public void setIdPemilik(String idPemilik) {
        this.idPemilik = idPemilik;
    }

}
