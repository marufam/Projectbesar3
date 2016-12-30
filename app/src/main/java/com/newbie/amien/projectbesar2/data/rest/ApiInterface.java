package com.newbie.amien.projectbesar2.data.rest;

import com.newbie.amien.projectbesar2.data.retrofit.GetKost;
import com.newbie.amien.projectbesar2.data.retrofit.GetPemilik;
import com.newbie.amien.projectbesar2.data.retrofit.Pemilik;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Body;

/**
 * Created by amien on 28/12/16.
 */

public interface ApiInterface {
    @GET("kost")
    Call<GetKost> getKost();

    @GET("kost/cari/{valuenya}")
    Call<GetKost> getKostcari(@Path("valuenya") String valuenya);

    @GET("pemilik")
    Call<GetPemilik> getPemilik();

    @GET("pemilik/{valuenya}")
    Call<GetPemilik> getPemilikcari(@Path("valuenya") String valuenya);

    @Headers("Content-Type: application/json")
    @POST("pemilik/login")
    Call<GetPemilik> postLogin(@Body() Pemilik pemilik);

//    @FormUrlEncoded
//    @POST("kost")
//    Call<PostPutDelPembelian> postPembelian(@Field("id_pembelian") String idPembelian,
//                                            @Field("id_pembeli") String idPembeli,
//                                            @Field("tanggal_beli") String tanggalBeli,
//                                            @Field("total_harga") String totalHarga,
//                                            @Field("id_tiket") String idTiket);
//    @FormUrlEncoded
//    @PUT("pembelian/user")
//    Call<PostPutDelPembelian> putPembelian(@Field("id_pembelian") String idPembelian,
//                                           @Field("id_pembeli") String idPembeli,
//                                           @Field("tanggal_beli") String tanggalBeli,
//                                           @Field("total_harga") String totalHarga,
//                                           @Field("id_tiket") String idTiket);
//    @FormUrlEncoded
//    @HTTP(method = "DELETE", path = "pembelian/user", hasBody = true)
//    Call<PostPutDelPembelian> deletePembelian(@Field("id_pembelian") String idPembelian);
}