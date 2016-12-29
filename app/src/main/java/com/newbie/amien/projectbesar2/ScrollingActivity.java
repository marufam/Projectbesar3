package com.newbie.amien.projectbesar2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
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
import com.squareup.picasso.Picasso;

public class ScrollingActivity extends AppCompatActivity {
    String longtitude, latitude, nama;
    TextView nama_kost, harga, alamat, fasilitas, keterangan, jumlah_kamar,  image;
    ImageView img;
    MapView mMapDetail;

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
        fasilitas= (TextView) findViewById(R.id.fasilitas);
        keterangan = (TextView) findViewById(R.id.deskripsi);
        jumlah_kamar = (TextView) findViewById(R.id.jumlah_kamar);

        nama_kost.setText(i.getStringExtra("nama_kost"));
        alamat.setText(i.getStringExtra("alamat_kost"));
        fasilitas.setText(i.getStringExtra("fasilitas"));
        keterangan.setText(i.getStringExtra("keterangan"));
        jumlah_kamar.setText(i.getStringExtra("jumlah_kamar"));

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

        Toast.makeText(this, ""+i.getStringExtra("image"), Toast.LENGTH_SHORT).show();
        Picasso.with(getApplicationContext())
                .load(i.getStringExtra("image"))
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
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
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
