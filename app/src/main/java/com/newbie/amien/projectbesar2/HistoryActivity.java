package com.newbie.amien.projectbesar2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.newbie.amien.projectbesar2.Home.adapter.KostAdapter;
import com.newbie.amien.projectbesar2.data.rest.ApiClient;
import com.newbie.amien.projectbesar2.data.rest.ApiInterface;
import com.newbie.amien.projectbesar2.data.retrofit.GetHistory;
import com.newbie.amien.projectbesar2.data.retrofit.GetKost;
import com.newbie.amien.projectbesar2.data.retrofit.History;
import com.newbie.amien.projectbesar2.data.retrofit.Kost;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivity extends AppCompatActivity {
    private RecyclerView mRecycleview;
    private RecyclerView.LayoutManager mLayoutmanager;
    private RecyclerView.Adapter mAdapter;
    ApiInterface mApiInterface;
//    private List<History> myhistory=new ArrayList<>();
    private List<Kost> mykost= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecycleview = (RecyclerView) findViewById(R.id.r_history);
        mLayoutmanager = new LinearLayoutManager(this);
        mRecycleview.setLayoutManager(mLayoutmanager);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        String id_user = pref.getString("Id",null);
//        Toast.makeText(this, ""+id_user, Toast.LENGTH_LONG).show();
        ApiInterface mApiInterface = ApiClient.crudHistory().create(ApiInterface.class);
        Call<GetHistory> historyCall = mApiInterface.getHistoryId(id_user.toString());
        historyCall.enqueue(new Callback<GetHistory>() {
            @Override
            public void onResponse(Call<GetHistory> call, Response<GetHistory> response) {
                ApiInterface mApiInterface;
                List<History> kostList = response.body().getHistory();
                Log.d("Retrofit Get", "Jumlah data History: " +
                        String.valueOf(kostList.size()));
//                Toast.makeText(HistoryActivity.this, "Success", Toast.LENGTH_SHORT).show();
                List<com.newbie.amien.projectbesar2.data.retrofit.History> r_history = response.body().getHistory();
//                Toast.makeText(HistoryActivity.this, "Jumlahh "+r_history.size(), Toast.LENGTH_SHORT).show();
                for (int i =0; i<r_history.size(); i++){



                    mApiInterface = ApiClient.getClient().create(ApiInterface.class);
                    Call<GetKost> kostCall = mApiInterface.getKostId(r_history.get(i).getIdKost());
//                    Toast.makeText(HistoryActivity.this, "haahha "+r_history.get(i).getIdKost(), Toast.LENGTH_SHORT).show();
                    kostCall.enqueue(new Callback<GetKost>() {
                        @Override
                        public void onResponse(Call<GetKost> call, Response<GetKost> response) {
                            List<com.newbie.amien.projectbesar2.data.retrofit.Kost> r_kostlist = response.body().getKost();
//                            Toast.makeText(HistoryActivity.this, "Jumlah "+r_kostlist.size(), Toast.LENGTH_SHORT).show();

                            mykost.add(r_kostlist.get(0));
                            mAdapter = new HistoryAdapter(mykost);
                            mRecycleview.setAdapter(mAdapter);
                        }

                        @Override
                        public void onFailure(Call<GetKost> call, Throwable t) {
                            Log.e("Retrofit Get", t.toString());
                        }


                    });


                }
            }

            @Override
            public void onFailure(Call<GetHistory>call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
                Toast.makeText(HistoryActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
            }


        });





//        Toast.makeText(this, "Jumlah 1 "+myhistory.size(), Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, "Jumlah 2 "+mykost.size(), Toast.LENGTH_SHORT).show();

    }

    public void historiku(){

    }

    public void kostku(){

    }
}
