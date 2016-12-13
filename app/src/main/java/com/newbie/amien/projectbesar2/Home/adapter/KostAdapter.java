package com.newbie.amien.projectbesar2.Home.adapter;


import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.newbie.amien.projectbesar2.Home.model.Kost;
import com.newbie.amien.projectbesar2.R;

import java.util.List;

/**
 * Created by amien on 12/12/16.
 */

public class KostAdapter
        extends RecyclerView.Adapter
        <KostAdapter.ListItemViewHolder> {

    private List<Kost> items;
    private SparseBooleanArray selectedItems;

    public KostAdapter(List<Kost> modelData) {
        if (modelData == null) {
            throw new IllegalArgumentException("modelData must not be null");
        }
        items = modelData;
        selectedItems = new SparseBooleanArray();
    }

    @Override
    public ListItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.recycler_list, viewGroup, false);

        return new ListItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListItemViewHolder viewHolder, int position) {
        final Kost model = items.get(position);
        viewHolder.name.setText(String.valueOf(model.nama_kost));
//        viewHolder.name.setOnClickListener(new View.OnClickListener() {
//            @Override
//
//            public void onClick(View view) {
//                Toast.makeText(view.getContext(), "Nama Kost "+String.valueOf(model.nama_kost), Toast.LENGTH_SHORT).show();
//            }
//        });
        viewHolder.itemView.setActivated(selectedItems.get(position, false));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public final static class ListItemViewHolder extends RecyclerView.ViewHolder {
        TextView name;

        public ListItemViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.txt_namakost);
        }
    }
}
