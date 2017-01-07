package com.newbie.amien.projectbesar2.Home.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.newbie.amien.projectbesar2.Home.adapter.KostAdapter;
import com.newbie.amien.projectbesar2.R;
import com.newbie.amien.projectbesar2.ScrollingActivity;
import com.newbie.amien.projectbesar2.data.rest.ApiClient;
import com.newbie.amien.projectbesar2.data.rest.ApiInterface;
import com.newbie.amien.projectbesar2.data.retrofit.GetKost;
import com.newbie.amien.projectbesar2.data.retrofit.Kost;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.newbie.amien.projectbesar2.R.drawable.marker2;

/**
 * Created by amien on 11/12/16.
 */

public class MapFragment extends Fragment implements LocationListener {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutmanager;
    private RecyclerView.Adapter mAdapter;
    private List<Kost> myKosts = new ArrayList<>();
    private Kost kost;
    LocationManager locationManager;
    String mprovider;
    ApiInterface mApiInterface;
    SearchView search;
    LatLng myloca;
    MapView mMapView;
    private GoogleMap googleMap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.map_layout, container, false);
        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        mprovider = locationManager.getBestProvider(criteria, false);

        if (mprovider != null && !mprovider.equals("")) {
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return null;
            }
            Location location = locationManager.getLastKnownLocation(mprovider);
            locationManager.requestLocationUpdates(mprovider, 360000, 1, this);

            if (location != null)
                onLocationChanged(location);
            else
                Toast.makeText(getContext(), "No Location Provider Found Check Your Code", Toast.LENGTH_SHORT).show();
        }
        prepareallmap();
        return rootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        search=(SearchView) getView().findViewById(R.id.searchView1);
        search.setQueryHint("Pencarian");
        search.setOnQueryTextListener(new OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                mApiInterface = ApiClient.getClient().create(ApiInterface.class);
                Call<GetKost> kostCall = mApiInterface.getKostcari(search.getQuery().toString());
                kostCall.enqueue(new Callback<GetKost>() {
                    @Override
                    public void onResponse(Call<GetKost> call, Response<GetKost> response) {

                        List<com.newbie.amien.projectbesar2.data.retrofit.Kost> r_kostlist = response.body().getKost();
                        myKosts=r_kostlist;
                        googleMap.clear();
                        Toast.makeText(getContext(), ""+myKosts.size()+":"+myKosts.get(0).getNamaKost(), Toast.LENGTH_LONG).show();
                        for (int i=0; i<myKosts.size(); i++){

                            String harga = null;
                            if(myKosts.get(i).getHarga().length()>=7){
                                harga = myKosts.get(i).getHarga().substring(0,1);
                                if(myKosts.get(i).getHarga().substring(1,2).equals("0")==false){
                                    harga+=","+myKosts.get(i).getHarga().substring(1,2)+"jt";
                                }else{
                                    harga+="jt";
                                }

                            }else if(myKosts.get(i).getHarga().length()>=6){
                                harga = myKosts.get(i).getHarga().substring(0,3)+"rb";
                            }else if(myKosts.get(i).getHarga().length()>=5){
                                harga = myKosts.get(i).getHarga().substring(0,2)+"rb";
                            }else if(myKosts.get(i).getHarga().length()>=4){
                                harga = myKosts.get(i).getHarga().substring(0,1)+"rb";
                            }

                            googleMap.addMarker(new MarkerOptions()
                                    .position(new LatLng(Double.parseDouble(myKosts.get(i).getLatitude()), Double.parseDouble(myKosts.get(i).getLongtitude())))
                                    .title(myKosts.get(i).getNamaKost()).snippet(harga).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)))
                                    .setTag(myKosts.get(i));
                            CameraPosition cameraPosition;
                            // For dropping a marker at a point on the Map

                                LatLng myLocation = new LatLng(Double.parseDouble(myKosts.get(0).getLatitude())
                                        , Double.parseDouble(myKosts.get(0).getLongtitude()));
                                cameraPosition = new CameraPosition.Builder().target(myLocation).zoom(14).build();
                                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                            CameraPosition PosisiTempat;
                        }
                    }

                    @Override
                    public void onFailure(Call<GetKost> call, Throwable t) {
                        Log.e("Error Retrofit Get", t.toString());
                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        search.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                prepareallmap();
                return false;
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }


    @Override
    public void onLocationChanged(Location location) {

        myloca = new LatLng(location.getLatitude(),location.getLongitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public void prepareallmap(){
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                // For showing a move to my location button
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    return;
                }
                googleMap.setMyLocationEnabled(true);
                CameraPosition cameraPosition;
                // For dropping a marker at a point on the Map
                if(myloca!=null) {
                    LatLng myLocation = new LatLng(myloca.latitude, myloca.longitude);
                    cameraPosition = new CameraPosition.Builder().target(myLocation).zoom(14).build();
                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                }

                CameraPosition PosisiTempat;



                mApiInterface = ApiClient.getClient().create(ApiInterface.class);
                Call<GetKost> kostCall = mApiInterface.getKost();
                kostCall.enqueue(new Callback<GetKost>() {
                    @Override
                    public void onResponse(Call<GetKost> call, Response<GetKost> response) {
                        List<com.newbie.amien.projectbesar2.data.retrofit.Kost> kostList = response.body().getKost();
                        Log.d("Retrofit Get", "Jumlah data pembelian: " +
                                String.valueOf(kostList.size()));
                        List<com.newbie.amien.projectbesar2.data.retrofit.Kost> r_kostlist = response.body().getKost();
                        myKosts=r_kostlist;

                        for (int i=0; i<myKosts.size(); i++){

                            String harga = null;
                            if(myKosts.get(i).getHarga().length()>=7){
                                harga = myKosts.get(i).getHarga().substring(0,1);
                                if(myKosts.get(i).getHarga().substring(1,2).equals("0")==false){
                                    harga+=","+myKosts.get(i).getHarga().substring(1,2)+"jt";
                                }else{
                                    harga+="jt";
                                }

                            }else if(myKosts.get(i).getHarga().length()>=6){
                                harga = myKosts.get(i).getHarga().substring(0,3)+"rb";
                            }else if(myKosts.get(i).getHarga().length()>=5){
                                harga = myKosts.get(i).getHarga().substring(0,2)+"rb";
                            }else if(myKosts.get(i).getHarga().length()>=4){
                                harga = myKosts.get(i).getHarga().substring(0,1)+"rb";
                            }

                            googleMap.addMarker(new MarkerOptions()
                                    .position(new LatLng(Double.parseDouble(myKosts.get(i).getLatitude()), Double.parseDouble(myKosts.get(i).getLongtitude())))
                                    .title(myKosts.get(i).getNamaKost()).snippet(harga).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)))
                                    .setTag(myKosts.get(i));


                        }

                    }

                    @Override
                    public void onFailure(Call<GetKost> call, Throwable t) {
                        Log.e("Error Retrofit Get", t.toString());
                    }


                });

                googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(final Marker marker) {
                        final Kost k= (Kost) marker.getTag();
//                            Toast.makeText(getContext(), "Kost " + k.getNamaKost(), Toast.LENGTH_SHORT).show();
                        Snackbar snackbar = Snackbar.make(getView(),"Kost "+marker.getTitle(),Snackbar.LENGTH_LONG )
                                .setActionTextColor(Color.parseColor("#FFAA00"))

                                .setAction("Lihat", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getContext(), ScrollingActivity.class);
                                        i.putExtra("id",k.getId());
                                        i.putExtra("nama_kost",k.getNamaKost());
                                        i.putExtra("tipe_kost",k.getTipeKost());
                                        i.putExtra("kota",k.getKota());
                                        i.putExtra("alamat_kost",k.getAlamat());
                                        i.putExtra("fasilitas",k.getFasilitas());
                                        i.putExtra("harga",k.getHarga());
                                        i.putExtra("keterangan",k.getKeterangan());
                                        i.putExtra("jumlah_kamar",k.getJumlahKamar());
                                        i.putExtra("tersedia",k.getTersedia());
                                        i.putExtra("longtitude",k.getLongtitude());
                                        i.putExtra("latitude",k.getLatitude());
                                        i.putExtra("image",k.getImage());
                                        i.putExtra("namapemilik",k.getNamaPemilik());
                                        i.putExtra("telepon",k.getTelepon());
                                        v.getContext().startActivity(i);
                                    }
                                });
                        snackbar.show();
                        return false;
                    }
                });

            }
        });
    }
}

