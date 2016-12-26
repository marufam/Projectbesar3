package com.newbie.amien.projectbesar2.Home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.newbie.amien.projectbesar2.Home.adapter.KostAdapter;
import com.newbie.amien.projectbesar2.Home.model.Kost;
import com.newbie.amien.projectbesar2.R;
import com.newbie.amien.projectbesar2.data.repository.KostDataRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amien on 11/12/16.
 */

public class ListFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private List<Kost> myKosts=new ArrayList<>();
    private Kost kost;
    private SearchView searchview;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.list_layout, null);

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        KostDataRepository jsonkost = new KostDataRepository();
        Toast.makeText(getContext(), ""+jsonkost.kostList().get(0).getNama_kost(), Toast.LENGTH_SHORT).show();
        System.out.println("===========================================+>>>>>>>>>>>>>>>>>>>>>"+jsonkost.kostList().size());
        recyclerView = (RecyclerView) getView().findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        for (int i=0; i<jsonkost.kostList().size(); i++){
            kost= new Kost(jsonkost.kostList().get(i).getId()
                    ,jsonkost.kostList().get(i).getNama_kost(),
                    jsonkost.kostList().get(i).getAlamat(),
                    jsonkost.kostList().get(i).getFasilias(),
                    jsonkost.kostList().get(i).getHarga(),
                    jsonkost.kostList().get(i).getKeterangan(),
                    jsonkost.kostList().get(i).getJumlah_kamar(),
                    jsonkost.kostList().get(i).getTersedia(),
                    jsonkost.kostList().get(i).getLongtitude(),
                    jsonkost.kostList().get(i).getLatitude(),
                    jsonkost.kostList().get(i).getImage());
            myKosts.add(kost);
        }

        mAdapter = new KostAdapter(myKosts);
        recyclerView.setAdapter(mAdapter);

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
