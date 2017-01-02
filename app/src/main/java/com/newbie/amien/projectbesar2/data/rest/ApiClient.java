package com.newbie.amien.projectbesar2.data.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by amien on 28/12/16.
 */

public class ApiClient {
    public static final String BASE_URL = "http://10.42.0.1:8080/";

    private static Retrofit retrofit = null;
    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private static Retrofit retrofit_pemilik = null;
    public static Retrofit getPemilik() {
        if (retrofit_pemilik==null) {
            retrofit_pemilik = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit_pemilik;
    }

    private static Retrofit login = null;
    public static Retrofit login() {
        if (login==null) {
            login = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return login;
    }

    private static Retrofit insertpemilik = null;
    public static Retrofit insertpemilik() {
        if (insertpemilik==null) {
            insertpemilik = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return insertpemilik;
    }
}
