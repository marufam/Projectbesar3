package com.newbie.amien.projectbesar2.data.rest;

import com.newbie.amien.projectbesar2.data.retrofit.GetKost;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by amien on 28/12/16.
 */

public interface ApiInterface {
    @GET("kost")
    Call<GetKost> getKost();

    @GET("kost/cari/{valuenya}")
    Call<GetKost> getKostcari(@Path("valuenya") String valuenya);

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