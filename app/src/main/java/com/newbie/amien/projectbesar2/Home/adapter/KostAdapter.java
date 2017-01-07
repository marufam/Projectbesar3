package com.newbie.amien.projectbesar2.Home.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.newbie.amien.projectbesar2.R;
import com.newbie.amien.projectbesar2.ScrollingActivity;
import com.newbie.amien.projectbesar2.data.retrofit.Kost;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by amien on 12/12/16.
 */

public class KostAdapter extends RecyclerView.Adapter<KostAdapter.ListItemViewHolder> {
    private Context context;
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
        context=viewGroup.getContext();
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.recycler_list, viewGroup, false);

        return new ListItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListItemViewHolder viewHolder, int position) {
//        context = viewHolder.imageView.getContext();
        final Kost model = items.get(position);
        viewHolder.nama_kost.setText(String.valueOf(model.getNamaKost()));
        Picasso.with(viewHolder.imageView.getContext())
                .load(model.getImage())
                .into(viewHolder.imageView);
        String harga = null;
        if(model.getHarga().length()>=7){
            harga = model.getHarga().substring(0,1);
            if(model.getHarga().substring(1,2).equals("0")==false){
                harga+=","+model.getHarga().substring(1,2)+"jt";
            }else{
                harga+="jt";
            }

        }else if(model.getHarga().length()>=6){
            harga = model.getHarga().substring(0,3)+"rb";
        }else if(model.getHarga().length()>=5){
            harga = model.getHarga().substring(0,2)+"rb";
        }else if(model.getHarga().length()>=4){
            harga = model.getHarga().substring(0,1)+"rb";
        }
        viewHolder.kamar.setText(String.valueOf(model.getTersedia())+" kamar tersisa ");
        viewHolder.harga_kost.setText(harga);
        viewHolder.tipe_kost.setText("Kost "+ String.valueOf(model.getTipeKost()));
        viewHolder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(view.getContext(), "Nama Kost "+String.valueOf(model.getNamaKost()), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(view.getContext(),ScrollingActivity.class);

                i.putExtra("id",model.getId());
                i.putExtra("nama_kost",model.getNamaKost());
                i.putExtra("tipe_kost",model.getTipeKost());
                i.putExtra("kota",model.getKota());
                i.putExtra("alamat_kost",model.getAlamat());
                i.putExtra("fasilitas",model.getFasilitas());
                i.putExtra("harga",model.getHarga());
                i.putExtra("keterangan",model.getKeterangan());
                i.putExtra("jumlah_kamar",model.getJumlahKamar());
                i.putExtra("tersedia",model.getTersedia());
                i.putExtra("longtitude",model.getLongtitude());
                i.putExtra("latitude",model.getLatitude());
                i.putExtra("image",model.getImage());
                i.putExtra("nama_pemilik",model.getNamaPemilik());
                i.putExtra("telepon",model.getTelepon());
                view.getContext().startActivity(i);
            }
        });
        viewHolder.itemView.setActivated(selectedItems.get(position, false));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public final static class ListItemViewHolder extends RecyclerView.ViewHolder {
        TextView nama_kost, harga_kost, kamar, tipe_kost;
        ImageView imageView;
        CardView cardview;


        public ListItemViewHolder(View itemView) {
            super(itemView);
            cardview = (CardView) itemView.findViewById(R.id.card_view);
            nama_kost = (TextView) itemView.findViewById(R.id.txt_namakost);
            harga_kost = (TextView) itemView.findViewById(R.id.harga_kost);
            kamar = (TextView) itemView.findViewById(R.id.txt_room);
            tipe_kost = (TextView) itemView.findViewById(R.id.tipe_kost);
            imageView = (ImageView) itemView.findViewById(R.id.gambar_kost);

        }
    }
}
