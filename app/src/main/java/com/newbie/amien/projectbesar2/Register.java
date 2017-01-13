package com.newbie.amien.projectbesar2;

import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.newbie.amien.projectbesar2.data.rest.ApiClient;
import com.newbie.amien.projectbesar2.data.rest.ApiInterface;
import com.newbie.amien.projectbesar2.data.retrofit.GetUser;
import com.newbie.amien.projectbesar2.data.retrofit.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    EditText namapemilik,alamatpemilik,telepon,email,password;
    Button btnsignup;
    TextInputLayout Ipassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inisialisasi();

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                if(pref.getString("Id", null).equals("0")){
                    simpankepemilik();
                }else{
                    Toast.makeText(Register.this, ""+pref.getString("Id", null), Toast.LENGTH_SHORT).show();
                    updatepemilik(pref.getString("Id", null).toString());
                }

            }
        });
    }
    protected void inisialisasi(){
        namapemilik = (EditText) findViewById(R.id.nama_pemilik);
        alamatpemilik = (EditText) findViewById(R.id.alamat_pemilik);
        telepon = (EditText) findViewById(R.id.telepon_pemilik);
        email = (EditText) findViewById(R.id.email_pemilik);
        password = (EditText) findViewById(R.id.password_pemilik);
        btnsignup = (Button) findViewById(R.id.signup_button);
        Ipassword = (TextInputLayout) findViewById(R.id.iPassword);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode

        if(pref.getString("Id",null).equals("0")){
            btnsignup.setText("Simpan");
            Ipassword.setVisibility(View.VISIBLE);
            password.setVisibility(View.VISIBLE);
                    setTitle("Register");
        }else{
            Toast.makeText(this, ""+pref.getString("Id", null).toString(), Toast.LENGTH_SHORT).show();
            btnsignup.setText("Update");
            Ipassword.setVisibility(View.INVISIBLE);
            password.setVisibility(View.INVISIBLE);
            readPemilik(pref.getString("Id", null).toString());
                    setTitle("Update");
        }

    }

    protected  void readPemilik(String Id){
        ApiInterface mApiInterface = ApiClient.login().create(ApiInterface.class);
        Call<GetUser> kostCall = mApiInterface.getPemilikcari(Id.toString());
        kostCall.enqueue(new Callback<GetUser>() {
            @Override
            public void onResponse(Call<GetUser> call, Response<GetUser> response) {
                Toast.makeText(Register.this, ""+response.body().getJumlah(), Toast.LENGTH_SHORT).show();
                namapemilik.setText(response.body().getUser().get(0).getNamaUser());
                alamatpemilik.setText(response.body().getUser().get(0).getAlamatUser());
                telepon.setText(response.body().getUser().get(0).getTelepon());
                email.setText(response.body().getUser().get(0).getEmail());
//                password.setText(response.body().getPemilik().get(0).getPassword());
            }

            @Override
            public void onFailure(Call<GetUser> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failed read   "+t, Toast.LENGTH_SHORT).show();
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

    protected void simpankepemilik(){
        if (namapemilik.getText().toString().isEmpty() &&
                alamatpemilik.getText().toString().isEmpty() &&
                telepon.getText().toString().isEmpty() &&
                email.getText().toString().isEmpty() &&
                password.getText().toString().isEmpty()){

            Toast.makeText(Register.this, "Lengkapi data dulu...", Toast.LENGTH_SHORT).show();
        }else{
            ApiInterface mApiInterface = ApiClient.login().create(ApiInterface.class);
            Call<GetUser> kostCall = mApiInterface.InsertPemilik(new User(
                    namapemilik.getText().toString(),
                    alamatpemilik.getText().toString(),
                    telepon.getText().toString(),
                    email.getText().toString(),
                    password.getText().toString()));
            kostCall.enqueue(new Callback<GetUser>() {
                @Override
                public void onResponse(Call<GetUser> call, Response<GetUser> response) {
                    Toast.makeText(getApplicationContext(), "Berhasil Terdaftar, Silahkan Login", Toast.LENGTH_LONG).show();
                    finish();

                }

                @Override
                public void onFailure(Call<GetUser> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Failed Simpan    "+t, Toast.LENGTH_SHORT).show();
                    Log.e("Retrofit Get", t.toString());
                }
            });
        }
    }
    protected void updatepemilik(final String Id){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        final String pass= pref.getString("Password", null).toString();
        if (namapemilik.getText().toString().isEmpty() &&
                alamatpemilik.getText().toString().isEmpty() &&
                telepon.getText().toString().isEmpty() &&
                email.getText().toString().isEmpty()
                ){

            Toast.makeText(Register.this, "Lengkapi data dulu...", Toast.LENGTH_SHORT).show();
        }else{

            SharedPreferences.Editor editor = pref.edit();
            editor.putString("Nama", namapemilik.getText().toString());
            editor.putString("Email", email.getText().toString());
            editor.commit();
            ApiInterface mApiInterface = ApiClient.login().create(ApiInterface.class);
            Call<GetUser> kostCall = mApiInterface.updatePemilik(new User(
                    Id.toString(),
                    namapemilik.getText().toString(),
                    alamatpemilik.getText().toString(),
                    telepon.getText().toString(),
                    email.getText().toString(),
                    pass.toString()));
            kostCall.enqueue(new Callback<GetUser>() {
                @Override
                public void onResponse(Call<GetUser> call, Response<GetUser> response) {
                    Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                    finish();
                }

                @Override
                public void onFailure(Call<GetUser> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_LONG).show();
                    finish();
//                    Toast.makeText(getApplicationContext(), "Failed update    "+t.getMessage(), Toast.LENGTH_SHORT).show();
//
//                    Log.e("Retrofit Get", t.toString());
                }
            });
        }
    }
}
