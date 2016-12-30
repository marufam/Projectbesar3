package com.newbie.amien.projectbesar2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.newbie.amien.projectbesar2.Home.adapter.KostAdapter;
import com.newbie.amien.projectbesar2.data.rest.ApiClient;
import com.newbie.amien.projectbesar2.data.rest.ApiInterface;
import com.newbie.amien.projectbesar2.data.retrofit.GetKost;
import com.newbie.amien.projectbesar2.data.retrofit.GetPemilik;
import com.newbie.amien.projectbesar2.data.retrofit.Kost;
import com.newbie.amien.projectbesar2.data.retrofit.Pemilik;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by amien on 19/12/16.
 */

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000; //milisecond

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);


        ApiInterface mApiInterface = ApiClient.login().create(ApiInterface.class);
        Call<GetPemilik> kostCall = mApiInterface.postLogin(new Pemilik("artdeffend@gmail.com","a"));
        kostCall.enqueue(new Callback<GetPemilik>() {
            @Override
            public void onResponse(Call<GetPemilik> call, Response<GetPemilik> response) {
                Toast.makeText(SplashActivity.this, ""+response, Toast.LENGTH_SHORT).show();
//                List<Kost> kostList = response.body().getKost();
//                Log.d("Retrofit Get", "Jumlah data pembelian: " +
//                        String.valueOf(kostList.size()));
//                List<com.newbie.amien.projectbesar2.data.retrofit.Kost> r_kostlist = response.body().getKost();
//
            }

            @Override
            public void onFailure(Call<GetPemilik> call, Throwable t) {
                Toast.makeText(SplashActivity.this, "Failde     "+t, Toast.LENGTH_SHORT).show();
                Log.e("Retrofit Get", t.toString());
            }


        });





        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish(); // keluar dari activity ini
            }
        }, SPLASH_TIME_OUT);
    }
}
