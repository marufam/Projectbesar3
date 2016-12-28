package com.newbie.amien.projectbesar2.Home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


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
                List<com.newbie.amien.projectbesar2.data.retrofit.Kost> kostList = response.body().getKost();
                Log.d("Retrofit Get", "Jumlah data pembelian: " +
                        String.valueOf(kostList.size()));
                List<com.newbie.amien.projectbesar2.data.retrofit.Kost> r_kostlist = response.body().getKost();
                mAdapter = new KostAdapter(r_kostlist);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetKost> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }


        });




//        searchview = (SearchView) getView().findViewById(R.id.searchViewList);

//        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                Toast.makeText(getContext(), "submit query", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                Toast.makeText(getContext(), "text Change", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });
    }


}
