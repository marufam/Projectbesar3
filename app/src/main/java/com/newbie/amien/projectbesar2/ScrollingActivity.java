package com.newbie.amien.projectbesar2;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.newbie.amien.projectbesar2.data.rest.ApiClient;
import com.newbie.amien.projectbesar2.data.rest.ApiInterface;
import com.newbie.amien.projectbesar2.data.retrofit.GetHistory;
import com.newbie.amien.projectbesar2.data.retrofit.GetKost;
import com.newbie.amien.projectbesar2.data.retrofit.History;
import com.newbie.amien.projectbesar2.data.retrofit.Kost;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScrollingActivity extends AppCompatActivity {
    String longtitude, latitude, nama;
    TextView nama_kost,tipe_kost, telepon, harga, alamat, fasilitas, keterangan, jumlah_kamar,nama_pemilik,  image;
    ImageView img;
    MapView mMapDetail;
    LinearLayout i_kasur, i_almari, i_kmandi, i_meja, i_kursi, i_televisi, i_ac;
    ApiInterface mApiInterface;
    private GoogleMap googleMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        nama_kost = (TextView) findViewById(R.id.nama_kost);
        alamat = (TextView) findViewById(R.id.alamat);
//        fasilitas= (TextView) findViewById(R.id.fasilitas);
        keterangan = (TextView) findViewById(R.id.deskripsi);
        jumlah_kamar = (TextView) findViewById(R.id.jumlah_kamar);
        tipe_kost = (TextView) findViewById(R.id.tipe_kost);
        telepon = (TextView) findViewById(R.id.telepon);
        nama_pemilik = (TextView) findViewById(R.id.nama_pemilik);
        nama_kost.setText(i.getStringExtra("nama_kost"));
        alamat.setText(i.getStringExtra("alamat_kost")+", "+i.getStringExtra("kota"));
//        fasilitas.setText(i.getStringExtra("fasilitas"));
        keterangan.setText(i.getStringExtra("keterangan"));
        jumlah_kamar.setText(i.getStringExtra("jumlah_kamar"));
        tipe_kost.setText("Kost "+i.getStringExtra("tipe_kost"));
        telepon.setText(i.getStringExtra("telepon"));
        nama_pemilik.setText(i.getStringExtra("nama_pemilik"));
        aturfasilitas();

        latitude=i.getStringExtra("latitude");
        longtitude=i.getStringExtra("longtitude");

        harga = (TextView) findViewById(R.id.harga_detail);
        String m_harga=null;
        String s_harga =i.getStringExtra("harga");
        if(i.getStringExtra("harga").length()>=7){
            m_harga = s_harga.substring(0,1);
            if(s_harga.substring(1,2).equals("0")==false){
                m_harga+=","+s_harga.substring(1,2)+"jt";
            }else{
                m_harga+="jt";
            }

        }else if(s_harga.length()>=6){
            m_harga = s_harga.substring(0,3)+"rb";
        }else if(s_harga.length()>=5){
            m_harga = s_harga.substring(0,2)+"rb";
        }else if(s_harga.length()>=4){
            m_harga = s_harga.substring(0,1)+"rb";
        }
        harga.setText(m_harga.toString());
        img = (ImageView) findViewById(R.id.header_logo);

        getSupportActionBar().setTitle("");
        ApiClient a = new ApiClient(); //buat ngambil alamat

//        Toast.makeText(this, ""+"http://localhost:8080/public/"+i.getStringExtra("image"), Toast.LENGTH_LONG).show();
        Picasso.with(getApplicationContext())
                .load(a.BASE_URL.toString()+"public/"+i.getStringExtra("image"))
                .into(img);
        mMapDetail = (MapView) findViewById(R.id.mapDetail);
        mMapDetail.onCreate(savedInstanceState);
        mMapDetail.onResume();


        try {
            MapsInitializer.initialize(getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mMapDetail.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                // For showing a move to my location button
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    return;
                }
                googleMap.setMyLocationEnabled(true);
                CameraPosition cameraPosition;
                // For dropping a marker at a point on the Map
                LatLng forsa = new LatLng(Double.parseDouble(latitude),Double.parseDouble(longtitude));
                googleMap.addMarker(new MarkerOptions().position(forsa).title(nama).snippet(harga.getText().toString()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
                cameraPosition = new CameraPosition.Builder().target(forsa).zoom(12).build();

                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });


        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        if(!pref.getString("Id", null).equals("0")) {

            mApiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<GetHistory> kostCall = mApiInterface.postHistory(new History(i.getStringExtra("id").toString(),pref.getString("Id", null).toString()));
//                    Toast.makeText(HistoryActivity.this, "haahha "+r_history.get(i).getIdKost(), Toast.LENGTH_SHORT).show();
            kostCall.enqueue(new Callback<GetHistory>() {
                @Override
                public void onResponse(Call<GetHistory> call, Response<GetHistory> response) {
                    List<History> r_kostlist = response.body().getHistory();
//                            Toast.makeText(HistoryActivity.this, "Jumlah "+r_kostlist.size(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<GetHistory> call, Throwable t) {
                    Log.e("Retrofit Get", t.toString());
                }


            });
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    public void aturfasilitas(){

        Intent i = getIntent();
        i_kasur = (LinearLayout) findViewById(R.id.ic_kasur);
        i_almari = (LinearLayout) findViewById(R.id.ic_almari);
        i_kmandi = (LinearLayout) findViewById(R.id.ic_kamarmandi);
        i_meja  = (LinearLayout) findViewById(R.id.ic_meja);
        i_kursi = (LinearLayout) findViewById(R.id.ic_kursi);
        i_televisi = (LinearLayout) findViewById(R.id.ic_televisi);
        i_ac = (LinearLayout) findViewById(R.id.ic_ac);
        matikanfasilitas();
        String fasilitas= i.getStringExtra("fasilitas");
        if(!fasilitas.isEmpty()){
            if(fasilitas.split(",")[0].equals("1")) {
                i_kasur.setVisibility(View.VISIBLE);
            }
            if(fasilitas.split(",")[1].equals("1")){
                i_almari.setVisibility(View.VISIBLE);
            }
            if(fasilitas.split(",")[2].equals("1")){
                i_kmandi.setVisibility(View.VISIBLE);
            }
            if(fasilitas.split(",")[3].equals("1")){
                i_meja.setVisibility(View.VISIBLE);
            }
            if(fasilitas.split(",")[4].equals("1")){
                i_kursi.setVisibility(View.VISIBLE);
            }
            if(fasilitas.split(",")[5].equals("1")){
                i_televisi.setVisibility(View.VISIBLE);
            }
            if(fasilitas.split(",")[6].equals("1")){
                i_ac.setVisibility(View.VISIBLE);
            }
        }
    }
    public void matikanfasilitas(){
        i_kasur.setVisibility(View.GONE);
        i_almari.setVisibility(View.GONE);
        i_kmandi.setVisibility(View.GONE);
        i_meja.setVisibility(View.GONE);
        i_kursi.setVisibility(View.GONE);
        i_televisi.setVisibility(View.GONE);
        i_ac.setVisibility(View.GONE);
    }



//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
