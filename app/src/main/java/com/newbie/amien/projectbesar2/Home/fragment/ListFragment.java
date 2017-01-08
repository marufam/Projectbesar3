package com.newbie.amien.projectbesar2.Home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.newbie.amien.projectbesar2.Home.adapter.KostAdapter;

import com.newbie.amien.projectbesar2.R;
import com.newbie.amien.projectbesar2.data.rest.ApiClient;
import com.newbie.amien.projectbesar2.data.rest.ApiInterface;
import com.newbie.amien.projectbesar2.data.retrofit.GetKost;
import com.newbie.amien.projectbesar2.data.retrofit.Kost;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by amien on 11/12/16.
 */

public class ListFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private List<Kost> myKosts=new ArrayList<>();
    private Kost kost;
    ApiInterface mApiInterface;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.list_layout, null);

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = (RecyclerView) getView().findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<GetKost> kostCall = mApiInterface.getKost();
        kostCall.enqueue(new Callback<GetKost>() {
            @Override
            public void onResponse(Call<GetKost> call, Response<GetKost> response) {
//                List<com.newbie.amien.projectbesar2.data.retrofit.Kost> kostList = response.body().getKost();
                List<com.newbie.amien.projectbesar2.data.retrofit.Kost> r_kostlist = response.body().getKost();
                myKosts=r_kostlist;
                mAdapter = new KostAdapter(r_kostlist);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetKost> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }


        });



        final SearchView searchview = (SearchView) getView().findViewById(R.id.search);
        searchview.setOnCloseListener(new SearchView.OnCloseListener() {

            @Override
            public boolean onClose() {
//                Toast.makeText(getContext(), "NgeClose", Toast.LENGTH_SHORT).show();
                List<Kost> myKosts_cari=new ArrayList<>();
                mAdapter = new KostAdapter(myKosts_cari);
                mAdapter = new KostAdapter(myKosts);
                recyclerView.setAdapter(mAdapter);
                return false;
            }
        });
        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            private List<Kost> myKosts_cari=new ArrayList<>();
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getContext(), "submit query ", Toast.LENGTH_SHORT).show();
                mApiInterface = ApiClient.getClient().create(ApiInterface.class);
                Call<GetKost> kostCall = mApiInterface.getKostcari(searchview.getQuery().toString());
                kostCall.enqueue(new Callback<GetKost>() {
                    @Override
                    public void onResponse(Call<GetKost> call, Response<GetKost> response) {

                        List<com.newbie.amien.projectbesar2.data.retrofit.Kost> r_kostlist = response.body().getKost();
                        Toast.makeText(getContext(), ""+r_kostlist.get(0).getKota(), Toast.LENGTH_SHORT).show();
                        mAdapter = new KostAdapter(myKosts_cari);
                        mAdapter = new KostAdapter(r_kostlist);
                        recyclerView.setAdapter(mAdapter);
                    }

                    @Override
                    public void onFailure(Call<GetKost> call, Throwable t) {
                        Log.e("Retrofit Get", t.toString());
                    }


                });

                return true;
            }


            @Override
            public boolean onQueryTextChange(String newText) {
//                Toast.makeText(getContext(), "text Change "+myKosts.size()+searchview.getQuery(), Toast.LENGTH_SHORT).show();
//                    for (int i=0; i<myKosts.size(); i++){
//                        if(searchview.getQuery().toString().isEmpty()==false && myKosts.get(i).getKota().equalsIgnoreCase(searchview.getQuery().toString())){
//                            mAdapter = new KostAdapter(myKosts_cari);
//                            myKosts_cari.add(myKosts.get(i));
//                            mAdapter = new KostAdapter(myKosts_cari);
//                            recyclerView.setAdapter(mAdapter);
//                            Toast.makeText(getContext(), "adadadada", Toast.LENGTH_SHORT).show();
//                        }else{
//                            mAdapter = new KostAdapter(myKosts);
//                            recyclerView.setAdapter(mAdapter);
//                            Toast.makeText(getContext(), "Tidak", Toast.LENGTH_SHORT).show();
//                        }
//                    }
                return true;
            }


        });
    }


}
