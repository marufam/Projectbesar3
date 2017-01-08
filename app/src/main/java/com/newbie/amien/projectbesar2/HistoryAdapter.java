package com.newbie.amien.projectbesar2;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.newbie.amien.projectbesar2.Home.adapter.KostAdapter;
import com.newbie.amien.projectbesar2.data.rest.ApiClient;
import com.newbie.amien.projectbesar2.data.rest.ApiInterface;
import com.newbie.amien.projectbesar2.data.retrofit.GetHistory;
import com.newbie.amien.projectbesar2.data.retrofit.GetKost;
import com.newbie.amien.projectbesar2.data.retrofit.History;
import com.newbie.amien.projectbesar2.data.retrofit.Kost;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by amien on 08/01/17.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder>  {

    private List<Kost> items;
    public HistoryAdapter(List<Kost> dataku){
        items = dataku;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_history,parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Kost model = items.get(position);
        final String kode_kost = model.getId();
        holder.nama_kost.setText(model.getNamaKost());
        holder.alamat_kost.setText(model.getAlamat());
        holder.cardview.setOnClickListener(new View.OnClickListener() {
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


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardview;
        TextView nama_kost, alamat_kost;
        public ViewHolder(View itemView) {
            super(itemView);
            cardview = (CardView) itemView.findViewById(R.id.h_card);
            nama_kost = (TextView) itemView.findViewById(R.id.h_nama_kost);
            alamat_kost = (TextView) itemView.findViewById(R.id.h_alamat);
        }
    }


}
