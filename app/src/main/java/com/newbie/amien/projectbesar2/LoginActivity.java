package com.newbie.amien.projectbesar2;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.newbie.amien.projectbesar2.data.rest.ApiClient;
import com.newbie.amien.projectbesar2.data.rest.ApiInterface;
import com.newbie.amien.projectbesar2.data.retrofit.GetPemilik;
import com.newbie.amien.projectbesar2.data.retrofit.Pemilik;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {



    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean status=attemptLogin();
//                Toast.makeText(LoginActivity.this, ""+status, Toast.LENGTH_SHORT).show();
                if(!status==true){
                    ApiInterface mApiInterface = ApiClient.login().create(ApiInterface.class);
                    Call<GetPemilik> kostCall = mApiInterface.postLogin(new Pemilik(mEmailView.getText().toString(),mPasswordView.getText().toString()));
                    kostCall.enqueue(new Callback<GetPemilik>() {
                        @Override
                        public void onResponse(Call<GetPemilik> call, Response<GetPemilik> response) {
                            Toast.makeText(getApplicationContext(), ""+response.body().getJumlah(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(LoginActivity.this, ""+response.body().getPemilik().get(0).getId(), Toast.LENGTH_SHORT).show();
                            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("Id", response.body().getPemilik().get(0).getId());
                            editor.putString("Email", response.body().getPemilik().get(0).getEmail());
                            editor.putString("Nama", response.body().getPemilik().get(0).getNamaPemilik());
                            editor.putString("Alamat", response.body().getPemilik().get(0).getAlamatPemilik());
                            editor.putString("Telepon", response.body().getPemilik().get(0).getTelepon());
                            editor.putString("Password", response.body().getPemilik().get(0).getPassword());
                            editor.commit();
                            Toast.makeText(LoginActivity.this, ""+response.body().getPemilik().get(0).getId(), Toast.LENGTH_SHORT).show();
                            SharedPreferences pref2 = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                            Toast.makeText(LoginActivity.this, ""+pref2.getString("Id", null), Toast.LENGTH_SHORT).show();
                            finish();



                        }

                        @Override
                        public void onFailure(Call<GetPemilik> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Failde     "+t, Toast.LENGTH_SHORT).show();
                            Log.e("Retrofit Get", t.toString());
                        }
                    });

                }
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        Button registerbutton = (Button) findViewById(R.id.register_button);
        registerbutton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Register.class);
                startActivity(i);
            }
        });
    }



    private boolean attemptLogin() {

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        }
        return cancel;
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() >= 1;
    }





}

