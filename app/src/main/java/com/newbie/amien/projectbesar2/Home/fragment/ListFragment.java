package com.newbie.amien.projectbesar2.Home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.newbie.amien.projectbesar2.Home.adapter.KostAdapter;
import com.newbie.amien.projectbesar2.Home.model.Kost;
import com.newbie.amien.projectbesar2.R;

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

        kost= new Kost("12","Forsa","malang","12","Forsa","malang","12","Forsa","malang","12","Forsa");
        myKosts.add(kost);

        kost= new Kost("12","Kontrakan murah malang","malang","12","Forsa","malang","12","Forsa","malang","12","Forsa");
        myKosts.add(kost);

        mAdapter = new KostAdapter(myKosts);
        recyclerView.setAdapter(mAdapter);
    }
}
